package com.zpq.bigincident.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    @JsonIgnore // 让springmvc把当前对象转换为json字符串时,忽略password
    private String password;
    private String nickName;
    private String email;
    private String userPic;
    private Date createTime;
    private Date updateTime;
}
