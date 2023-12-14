package com.zpq.bigincident.service;

import com.zpq.bigincident.pojo.User;

public interface UserService {

    User findByUserName(String username);

    void register(String username, String password);

    void updateUser(User user);

    void updateAvatar(String avatarUrl);
}
