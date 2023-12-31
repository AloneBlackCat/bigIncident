package com.zpq.bigincident.controller;

import com.zpq.bigincident.pojo.Result;
import com.zpq.bigincident.pojo.User;
import com.zpq.bigincident.service.impl.UserServiceImpl;
import com.zpq.bigincident.utils.JwtUtil;
import com.zpq.bigincident.utils.Md5Util;
import com.zpq.bigincident.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

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

    // 登录
    @PostMapping("/login")
    public <T> Result<T> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                               @Pattern(regexp = "^\\S{5,16}$") String password) {
        User loginUser = userService.findByUserName(username);
        // 根据用户名查询用户是否存在
        if (loginUser == null) {
            return Result.error("用户不存在");
        }
        // 判断密码是否正确
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            // 登录成功
            Map<String,Object> map = new HashMap<>();
            map.put("id",loginUser.getId());
            map.put("username", loginUser.getUsername());
            // jwt生成token
            String token = JwtUtil.getToken(map);
            // 将token存入redis中
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(loginUser.getId().toString(),token,1, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    // 获取用户的详细信息
    @GetMapping("/userInfo")
    public <T> Result<T> getUserInfo() {
        // 根据用户名查询用户
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    // 修改用户信息
    @PutMapping("/update")
    public <T> Result<T> updateUser(@RequestBody @Validated User user) {
        userService.updateUser(user);
        return Result.success();
    }

    // 更新用户头像
    @PatchMapping("/updateAvatar")
    public <T> Result<T> updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    // 更新用户密码
    @PatchMapping("/updatePwd")
    public <T> Result<T> updatePwd(@RequestBody Map<String,String> params) {
        // 校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少参数");
        }
        // 修改密码
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User LoginUser = userService.findByUserName(username);
        if (!LoginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码填写错误");
        }

        // newPwd和rePwd是否一致
        if (!rePwd.equals(newPwd)) {
            return Result.error("新密码不一致");
        }

        // 修改密码
        userService.updatePwd(newPwd);
        // 删除redis中的token
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.getOperations().delete(ThreadLocalUtil.getUserId().toString());
        return Result.success();
    }
}
