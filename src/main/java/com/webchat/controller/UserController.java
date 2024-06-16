package com.webchat.controller;

import com.webchat.pojo.Result;
import com.webchat.pojo.User;
import com.webchat.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/friends")
    public Result list(HttpSession session) {
        log.info("查询所有好友");
        int userId = (int)session.getAttribute("userId");
        List<User> userList = userService.list(userId);
        return Result.success(userList);
    }
    @GetMapping("/show")
    public Result show(HttpSession session) {
        log.info("查询所有信息");
        int userId = (int)session.getAttribute("userId");
       // log.info(userId);
        User user = userService.show(userId);
        return Result.success(user);
    }
    @PostMapping("/show")
    public Result save(@RequestBody User user) {
        log.info("更新个人信息");
        userService.update(user);
        return Result.success();
    }
    @PostMapping("/find")
    public Result getByNames(@RequestBody User user) {
        String nickName = user.getNickName();
        log.info(nickName + "你好");
        List<User> userList =  userService.getByNamesAll(nickName);
        if(userList.size() == 0) {
            return Result.success("无");
        }
        return Result.success(userList);
    }
}
