package com.portal.service;

import com.portal.pojo.User;

public interface UserService {
    // 根据用户名查询用户
    User findByUserName(String username);

    // 注册方法
    void register(String username, String password);
}
