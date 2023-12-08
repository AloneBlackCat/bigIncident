package com.zpq.bigincident.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter
@ToString
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
