package com.zpq.bigincident.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickName;
    private String email;
    private String userPic;
    private Date createTime;
    private Date updateTime;
}
