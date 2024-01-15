package com.portal.service.impl;

import com.portal.mapper.UserMapper;
import com.portal.pojo.User;
import com.portal.service.UserService;
import com.portal.utils.Md5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        return userMapper.selectOne(qw.eq("username",username));
    }

    @Override
    public void register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        User User = new User();
        User.setUsername(username);
        User.setPassword(md5String);
        userMapper.insert(User);
    }
}

