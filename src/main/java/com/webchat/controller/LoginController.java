package com.webchat.controller;

import com.webchat.pojo.Result;
import com.webchat.pojo.User;
import com.webchat.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpSession session) {
        User e = userService.login(user);
        if(e != null) {
            session.setAttribute("userId", e.getUserId());
            return Result.success(e);
        } else {
            return Result.error("用户名或密码错误");
        }
    }
}
