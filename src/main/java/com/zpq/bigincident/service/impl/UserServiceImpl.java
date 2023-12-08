package com.zpq.bigincident.service.impl;

import com.zpq.bigincident.mapper.UserMapper;
import com.zpq.bigincident.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String getUser() {
        return userMapper.getUser();
    }
}
