package com.zpq.bigincident.service.impl;

import com.zpq.bigincident.mapper.UserMapper;
import com.zpq.bigincident.pojo.User;
import com.zpq.bigincident.service.UserService;
import com.zpq.bigincident.utils.Md5Util;
import com.zpq.bigincident.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        // 密码加密
        String md5String = Md5Util.getMD5String(password);
        // 添加用户
        userMapper.register(username,md5String);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id =(Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String password) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id =(Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(password),id);
    }
}
