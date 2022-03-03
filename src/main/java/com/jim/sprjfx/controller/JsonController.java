package com.jim.sprjfx.controller;

import com.alibaba.fastjson.JSON;
import com.jim.sprjfx.entity.User;
import com.jim.sprjfx.service.FriendService;
import com.jim.sprjfx.service.UserService;
import com.jim.sprjfx.service.handleStatus;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

@Slf4j
@RestController
@RequestMapping("/sys")
public class JsonController {


    @Autowired
    private UserService userService;

    @Autowired
    private handleStatus handler;

    @Autowired
    FriendService friendService;

    @RequestMapping("/upload")
    public void upload(@RequestParam("file")MultipartFile file) throws Exception{
        String filePath = "E:\\testFiles\\"+file.getOriginalFilename();
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();

    }
    @RequestMapping("/download")
    public ResponseEntity download() throws Exception{
        FileSystemResource file = new FileSystemResource("abc.jpg");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","attactment;filename=123.jpg");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }


    /**
     * 接口：8080/shsw/json
     * 功能：接收json字符串
     * 开发：hsk
     * 时间：2022.2.25 9：56
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/login")
    public String login(@RequestBody JSONObject jsonObject) {
        log.info("JSON.Info--------------"+jsonObject.get("User"));
        LinkedHashMap<String,String> linkedHashMap = (LinkedHashMap<String, String>) jsonObject.get("User");
        String username = linkedHashMap.get("username");
        String password = linkedHashMap.get("password");
        try{
            log.info("用户"+userService.getUserByUserName(username));
            return "Success";
        }catch (Exception e){
            log.info("查找不到此用户");
            return "notFind";
        }
    }

    @PostMapping(value = "/checkUsername")
    public String checkUsername(@RequestBody JSONObject jsonObject) {
        LinkedHashMap<String,String> linkedHashMap = (LinkedHashMap<String, String>) jsonObject.get("User");
        String username = linkedHashMap.get("username");
        try{
            log.info("用户"+userService.getUserByUserName(username));
            return "Existed";
        }catch (Exception e){
            return "notExisted";
        }
    }


    @GetMapping(value="/test")
    @ResponseBody
    public String test(){
        System.out.println("/test");
        if(friendService.newFriend("123","456")){
            return "Success";
        }
        return "Error";
    }
    @PostMapping(value = "/addfriend")
    public User AddFriend(@RequestBody JSONObject jsonObject) {
        String username = (String) jsonObject.get("username");
        return userService.AddFriend(username);
    }

    @PostMapping(value = "/postjson")
    public String POSTJSON(@RequestBody JSONObject jsonObject) {
        /**
         * 解析出去
         */
        String status = (String) jsonObject.get("Status");
        LinkedHashMap<String,String> linkedHashMap = (LinkedHashMap<String, String>) jsonObject.get("User");
        String username = linkedHashMap.get("username");
        String password = linkedHashMap.get("password");
        String nickname = linkedHashMap.get("nickname");
        String mail     = linkedHashMap.get("mail");
        String phone    = linkedHashMap.get("phone");
        String info     = linkedHashMap.get("info");


        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");//设置当前时间的格式，为年-月-日
        String createtime = dateFormat.format(date);

        User user = new User();
        user.setNickname(nickname);
        user.setUsername(username);
        user.setPassword(password);
        user.setMail(mail);
        user.setPhone(phone);
        user.setInfo(info);
        user.setCreatetime(createtime);

        if("checkUsername".equals(status)){
            return handler.CheckUsername(user);
        }
        if("Login".equals(status)){
            return handler.Login(user);
        }
        if("Regist".equals(status)){
            return handler.Regist(user);
        }


        return null;
    }


    @GetMapping(value = "/jsonget")
    public String test(@RequestBody JSONObject jsonObject){
        return "Success";
    }
}
