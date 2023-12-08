package com.zpq.bigincident.controller;

import com.zpq.bigincident.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    //获取用户详细信息
    @GetMapping("/user/userInfo")
    public String getUserInfo() {
        return userService.getUser();
    }
}
