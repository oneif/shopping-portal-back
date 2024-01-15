package com.portal.controller;

import com.portal.pojo.Result;
import com.portal.utils.JwtUtil;
import com.portal.utils.Md5Util;
import com.portal.pojo.User;
import com.portal.service.UserService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{4,16}$") String username, @Pattern(regexp = "^\\S{4,16}$") String password) {
        // 查询用户名
        User user = userService.findByUserName(username);
        if (user == null) {
            // 注册
            userService.register(username, password);
            return Result.success("注册成功");
        } else {
            return Result.error("用户名已存在");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{4,16}$") String username, @Pattern(regexp = "^\\S{4,16}$") String password) {
        // 查询用户名
        User loginUser = userService.findByUserName(username);
        // 判断用户是否存在
        if (loginUser == null) return Result.error("用户名错误");
        // 判断密码是否正确
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.getToken(claims);

            return Result.success("登录成功", token);
        }
        return Result.error("密码错误");
    }
}
