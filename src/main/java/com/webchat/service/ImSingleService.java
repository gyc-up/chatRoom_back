package com.webchat.service;

import cn.hutool.core.lang.Dict;
import com.webchat.pojo.ChatRecord;

import java.util.List;

public interface ImSingleService {
    List<ChatRecord> findByUsername(String fromUser, String toUser);

    void add(ChatRecord chatRecord);

    Dict findUnReadNums(String toUsername);
}
