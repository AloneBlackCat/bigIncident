package com.zpq.bigincident.mapper;

import com.zpq.bigincident.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    // 根据用户名查询用户
    // @Select("select * from user where username = #{username}")
    User findByUserName(String username);

    // 添加用户
    // @Insert("insert into user(username,password,create_time,update_time)" +
    //        "values(#{username},#{password},now(),now())")
    void register(String username, String password);

    void updateUser(User user);

    void updateAvatar(@Param("userPic") String userPic,@Param("id") Integer id);
}
