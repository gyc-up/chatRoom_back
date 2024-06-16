package com.webchat.service.impl;

import cn.hutool.core.lang.Dict;
import com.webchat.mapper.ImSingleMapper;
import com.webchat.pojo.ChatRecord;
import com.webchat.service.ImSingleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImSingleServiceImpl implements ImSingleService {
    @Autowired
    private ImSingleMapper imSingleMapper;
    @Override
    public List<ChatRecord> findByUsername(String fromUser, String toUser) {
        List<ChatRecord> list = imSingleMapper.findByUsername(fromUser, toUser);
        list.forEach(x -> {
            if(x.getTouser().equals(fromUser) && x.getFromuser().equals(toUser)) {
                x.setReaded(1);
                imSingleMapper.updateByPrimaryKey(x);
            }
        });
        return list;
    }

    public Dict findUnReadNums(String toUsername) {
        List<ChatRecord> list = imSingleMapper.findByToUsername(toUsername);
        Map<String, List<ChatRecord>> collect = list.stream().collect(Collectors.groupingBy(ChatRecord::getFromuser));
        Dict dict = Dict.create();
        collect.forEach((k, v) -> dict.set(k, v.size()));
        return dict;
    }

    @Override
    public void add(ChatRecord chatRecord) {
        imSingleMapper.add(chatRecord);
    }
}
