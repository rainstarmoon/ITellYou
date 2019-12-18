package com.xiazeyu.itellyou.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ServerEndpoint("/ws/server/{userId}")
@Service
public class TestSocketServer {

    private static Set<Session> sessionConcurrentSet = ConcurrentHashMap.newKeySet();
    // private Set<Session> sessionConcurrentSet = Collections.newSetFromMap(new ConcurrentHashMap<>());

    private String userId;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        log.info("Session ID = {},连接开启.用户id = {}", session.getId(), userId);
        this.userId = userId;
        sessionConcurrentSet.add(session);
        sendMessage(session, "连接成功");
        log.info("当前连接数量:{}", sessionConcurrentSet.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        log.info("Session ID = {},连接关闭.用户id = {}", session.getId(), userId);
        sessionConcurrentSet.remove(session);
        log.info("当前连接数量:{}", sessionConcurrentSet.size());
    }

    /**
     * 出现错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
        error.printStackTrace();
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("Session ID = {},收到消息为{}.用户id = {}", session.getId(), message, userId);
        sendMessage(session, message);
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     *
     * @param session
     * @param message
     */
    private static void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)", message, session.getId()));
        } catch (IOException e) {
            log.error("发送消息出错：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 群发消息
     *
     * @param message
     * @throws IOException
     */
    public static void sendAll(String message) {
        for (Session session : sessionConcurrentSet) {
            if (session.isOpen()) {
                sendMessage(session, message);
            }
        }
    }

}
