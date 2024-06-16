package com.webchat.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.webchat.pojo.ChatRecord;
import com.webchat.service.ImSingleService;
import jakarta.annotation.Resource;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ServerEndpoint(value = "/imserverSingle/{nickName}")
@Component
public class WebSocketSingleServer implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(WebSocketSingleServer.class);

    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    @Resource
    ImSingleService imSingleService;

    static ImSingleService staticImSingleService;

    @OnOpen
    public void onOpen(Session session, @PathParam("nickName")String userId) {
        if(userId==null)    return;
        //判断该用户是否已经登,如果已经登录 则让另外一个设备下线
        if(sessionMap.get(userId)!=null){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type",-1);
            this.sendMessage(jsonObject.toString(),sessionMap.get(userId));
        }
        sessionMap.put(userId, session);
        log.info("[onOpen] 新建连接, session={}, 当前在线人数为：{}", session.getId(), sessionMap.size());
    }

    @OnClose
    public void onClose(Session session) {
        sessionMap.remove(session.getId());
        log.info("[onClose] 有一连接关闭, session={}, 当前在线人数为: {}", session.getId(), sessionMap.size());
    }
    @OnMessage
    public void onMessage(String message, Session fromSession) {
        log.info("服务器收到消息：{}", message);
        ChatRecord chatRecord = JSONUtil.toBean(message, ChatRecord.class);
        chatRecord.setTime(DateUtil.now());
        staticImSingleService.add(chatRecord);
        String jsonStr = JSONUtil.toJsonStr(chatRecord);
        String touser = chatRecord.getTouser();
        Session toSession = sessionMap.get(touser);
        this.sendMessage(jsonStr, toSession);
        log.info("[onMessage] 发送消息: {}", jsonStr);
    }
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("[onError]发生错误", error);
    }

    private void sendMessage(String message,Session toSession){
        try {
            toSession.getBasicRemote().sendText(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                log.info("服务器给客户端[{}]发送消息{}",session.getId(),message);
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            log.error("服务器发送消息给客户端失败", e);
        }
    }
    public void afterPropertiesSet() {
        staticImSingleService = imSingleService;
    }
}
