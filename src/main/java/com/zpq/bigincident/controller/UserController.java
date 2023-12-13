package com.zpq.bigincident.controller;

import com.zpq.bigincident.pojo.Result;
import com.zpq.bigincident.pojo.User;
import com.zpq.bigincident.service.impl.UserServiceImpl;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    // 注册用户
    @PostMapping("/register")
    public <T> Result<T> register(@Pattern(regexp = "^\\S{5,16}$") String username,
                                  @Pattern(regexp = "^\\S{5,16}$") String password){
        // 查询用户
        User user = userService.findByUserName(username);
        if (user == null) {
            // 没有占用
            userService.register(username, password);
            return Result.success();
        } else {
            // 用户名被占用
            return Result.error("当前用户名已经存在,请换一个吧");
        }
    }
}
