package com.webchat.controller;

import com.webchat.mapper.UserMapper;
import com.webchat.pojo.Result;
import com.webchat.pojo.User;
import com.webchat.service.FriendService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/friends")
public class FriendController {
    @Autowired
    private FriendService friendService;
    @PostMapping("/add")
    public Result add(@RequestBody User user, HttpSession session) {
        int userId = (int)session.getAttribute("userId");
        String nickName = user.getNickName();
       String msg =  friendService.add(nickName, userId);
       if(msg.equals("添加成功")) {
           return Result.success("添加成功");
       } else {
           return Result.error(msg);
       }
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody User user, HttpSession session) {
        int userId = (int)session.getAttribute("userId");
        String nickName = user.getNickName();
        String msg =  friendService.delete(nickName, userId);
        if(msg.equals("删除成功")) {
            return Result.success("删除成功");
        } else {
            return Result.error(msg);
        }
    }

    @PostMapping("/find")
    public Result getByNames(@RequestBody User user, HttpSession session) {
        int userId = (int)session.getAttribute("userId");
        String nickName = user.getNickName();
        log.info(nickName + "你好");
        List<User> userList =  friendService.getByNames(nickName, userId);
        if(userList.size() == 0) {
            return Result.success("无");
        }
        return Result.success(userList);
    }
}
