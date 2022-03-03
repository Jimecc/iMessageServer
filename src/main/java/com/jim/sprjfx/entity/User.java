package com.jim.sprjfx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  编码：hsk
 *  时间：2022年2月10日09:46:15
 * */
// get set 方法
@Data
// 有参构造
@AllArgsConstructor
// 无参构造
@NoArgsConstructor
public class User {
    private int id;
    private String nickname;
    private String username;
    private String password;
    private String salt;
    private String mail;
    private String avatar;
    private String info;
    private String statue;
    private String createtime;
    private String phone;

}
