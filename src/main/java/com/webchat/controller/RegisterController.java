package com.webchat.controller;

import com.webchat.pojo.Result;
import com.webchat.pojo.User;
import com.webchat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RegisterController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result Register(@RequestBody User user) {
        String e = userService.register(user);
        if(e.equals("注册成功")) {
            return Result.success(e);
        } else {
            return Result.error(e);
        }
    }
}
