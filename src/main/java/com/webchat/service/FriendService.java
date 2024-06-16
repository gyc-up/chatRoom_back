package com.webchat.service;

import com.webchat.pojo.User;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface FriendService {
    String add(String nickName, int userId);

    String delete(String nickName, int userId);


    List<User> getByNames(String nickName, int userId);
}
