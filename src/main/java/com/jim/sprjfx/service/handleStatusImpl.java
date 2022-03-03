package com.jim.sprjfx.service;

import com.jim.sprjfx.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class handleStatusImpl implements handleStatus{

    @Autowired
    private UserService userService;
    @Override
    public String CheckUsername(User user) {
        if(null == userService.getUserByUserName(user.getUsername())){
            return "notExisted";
        }else{
            return "Existed";
        }

    }

    @Override
    public String Login(User user) {

        String username = user.getUsername();
        String password = user.getPassword();
        try{
            User userdb = userService.getUserByUserName(username);
            String salt = userdb.getSalt();
            String passworddb = userdb.getPassword();

            String passwordencry = PasswordUtil.encrypt(username,password,salt);

            if(passworddb.equals(passwordencry)){
                return "Success";
            }else{
                log.info("登录失败---密码错误");
                return "ErrorPassword";
            }
        }catch (Exception e){
            log.info("登录失败---查找不到此用户"+e);
            return "notExisted";
        }
    }

    @Override
    public String Regist(User user) {

        String salt = PasswordUtil.randomGen(8);
        String username = user.getUsername();
        String password = user.getPassword();

        user.setSalt(salt);
        String passwordencry = PasswordUtil.encrypt(username,password,salt);
        String a = PasswordUtil.encrypt(username,password,salt);
        user.setPassword(a);
        try{
            if(userService.newUser(user)) {
                log.info("用户："+user.getUsername()+"注册成功");
                return "Success";
            }else{
                return "Error";
            }
        }catch (Exception e){
            return  "Error";
        }
    }


}
