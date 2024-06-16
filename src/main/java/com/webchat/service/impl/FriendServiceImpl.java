package com.webchat.service.impl;

import com.webchat.mapper.FriendMapper;
import com.webchat.mapper.UserMapper;
import com.webchat.pojo.Friend;
import com.webchat.pojo.User;
import com.webchat.service.FriendService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public String add(String nickName, int userId) {
        User user = userMapper.getByName(nickName);
        if(user == null) {
            return "查无此人";
        } else {
            Friend friend = friendMapper.getShip(userId, user.getUserId());
            if(friend != null) {
                return "已经是好友，无需重复添加";
            }
            friendMapper.add(nickName, userId, user.getUserId());
            friendMapper.add(nickName, user.getUserId(), userId);
            return "添加成功";
        }
    }

    @Override
    public String delete(String nickName, int userId) {
        User user = userMapper.getByName(nickName);
        if(user == null) {
            return "查无此人";
        } else {
            Friend friend = friendMapper.getShip(userId, user.getUserId());
            if(friend == null) {
                return "不是好友，无需删除";
            }
            friendMapper.delete(userId, user.getUserId());
            friendMapper.delete(user.getUserId(), userId);
            return "删除成功";
        }
    }

    @Override
    public List<User> getByNames(String nickName, int userId) {
        List<User> userList = userMapper.getByNames(nickName, userId);
        return userList;
    }


}
