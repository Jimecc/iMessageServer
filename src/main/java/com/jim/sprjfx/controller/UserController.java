package com.jim.sprjfx.controller;

import com.jim.sprjfx.entity.User;
import com.jim.sprjfx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> index(){

        List<User> list = userService.listUsers();
        return list;
    }

    @GetMapping("/test")
    public void test(){
        User user = userService.getUserByUserName("admin");
    }

    /**
     *  接口：/jsonfun
     *  功能：接收JSON请求并转发
     *  开发：hsk
     *  时间：2022年2月14日09:34:37
     */
    @RequestMapping(value = "/jsonfun")
    @ResponseBody
    public List<User> jsonFun(JSONObject jsonObject) {
        log.info("Get JSON Success "+jsonObject);
        return null;
    }
}
