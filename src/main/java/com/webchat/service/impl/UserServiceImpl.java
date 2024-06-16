package com.webchat.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.webchat.common.MD5Utils;
import com.webchat.mapper.UserMapper;
import com.webchat.pojo.User;
import com.webchat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> list(int userId) {
        List<User> userList = userMapper.list(userId);
        return userList;
    }

    @Override
    public User login(User user) {
        String password = MD5Utils.code(user.getPassword());
        user.setPassword(password);
        User loginUser = userMapper.getByUsernameAndPassword(user);
        return loginUser;
    }

    @Override
    public User show(int userId) {
        User user = userMapper.show(userId);
        return user;
    }

    @Override
    public String register(User user) {
        String nickName = user.getNickName();
        String email = user.getEmail();
        User e = userMapper.getByName(nickName);
        if(e != null) {
            return "用户名重复";
        } else {
            User a = userMapper.getByEmail(email);
            if(a != null) {
                return "邮箱重复";
            }
            if(ObjectUtil.isEmpty(user.getAvatar())) {
                user.setAvatar("http://localhost:8080/files/default.jpg");
            }
            String password = MD5Utils.code(user.getPassword());
            user.setPassword(password);
            userMapper.register(user);
            return "注册成功";
        }
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public List<User> getByNamesAll(String nickName) {
        List<User> userList = userMapper.getByNamesAll(nickName);
        return userList;
    }
}
