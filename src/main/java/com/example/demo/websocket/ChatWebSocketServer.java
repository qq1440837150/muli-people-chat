package com.example.demo.websocket;

import com.example.demo.service.GroupMessageService;
import com.example.demo.util.SpringUtil;
import com.example.demo.viewobj.GroupMessageView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.context.SpringContextUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint ("/chat/{groupId}/{userId}")
@Component
@Slf4j
public class ChatWebSocketServer {
    GroupMessageService groupMessageService;
    private static Map<String, Session> clients = new ConcurrentHashMap<> ();
    @OnOpen
    public void onOpen(Session session,@PathParam (value = "groupId") Integer groupId,
                       @PathParam (value = "userId")String userId) throws JsonProcessingException {
        if(groupMessageService==null){
            groupMessageService = SpringUtil.getBean (GroupMessageService.class );
        }
        log.info("有新的客户端连接了: {}", session.getId());
        //将新用户存入在线的组
        List<GroupMessageView> groupMessageViewList = groupMessageService.selectGroupMessageViewByGroupIdOrderByRecordTime (groupId,1,10);
        ObjectMapper objectMapper = new ObjectMapper ();
        String objects = objectMapper.writeValueAsString (groupMessageViewList);
        log.info (objects);
        clients.put(session.getId(), session);
        sendAll ("【系统消息】"+userId+"进入群聊");
        sendAll (objects);
    }

    /**
     * 客户端关闭
     * @param session session
     */
    @OnClose
    public void onClose(Session session,@PathParam ("groupId") String groupId,@PathParam ("userId")String userId) {

        log.info("有用户断开了, id为:{}", session.getId());
        //将掉线的用户移除在线的组里
        clients.remove(session.getId());
    }

    /**
     * 发生错误
     * @param throwable e
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 收到客户端发来消息
     * @param message  消息对象
     */
    @OnMessage
    public void onMessage(String message,@PathParam ("groupId") String groupId,@PathParam ("userId")String userId) {
        log.info("服务端收到客户端发来的消息: {}", message);
        this.sendAll(userId+"|"+ message);
    }

    /**
     * 群发消息
     * @param message 消息内容
     */
    private void sendAll(String message) {
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            sessionEntry.getValue().getAsyncRemote().sendText(message);
        }
    }
}
