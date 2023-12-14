package com.zpq.bigincident.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    @NotNull
    private Integer id;
    private String username;
    @JsonIgnore // 让springmvc把当前对象转换为json字符串时,忽略password
    private String password;
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickName;
    @NotEmpty
    @Email
    private String email;
    private String userPic;
    private Date createTime;
    private Date updateTime;
}
