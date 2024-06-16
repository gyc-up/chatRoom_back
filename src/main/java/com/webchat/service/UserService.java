package com.webchat.service;

import com.webchat.pojo.User;

import java.util.List;

public interface UserService {
    List<User> list(int userId);

    User login(User user);

    User show(int userId);

    String register(User user);

    void update(User user);

    List<User> getByNamesAll(String nickName);
}
