package com.webchat.controller;

import cn.hutool.core.lang.Dict;
import com.webchat.pojo.ChatRecord;
import com.webchat.pojo.Result;
import com.webchat.service.ImSingleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/imsingle")
public class ImSingleController {
    @Resource
    private ImSingleService imSingleService;
    @GetMapping
    public Result findByFromUsername(@RequestParam String fromUser, @RequestParam String toUser) {
        List<ChatRecord> all = imSingleService.findByUsername(fromUser, toUser);
        return Result.success(all);

    }

    @GetMapping("/unReadNums")
    public Result findUnReadNums(@RequestParam String toUsername) {
        Dict dict = imSingleService.findUnReadNums(toUsername);
        return Result.success(dict);
    }
}
