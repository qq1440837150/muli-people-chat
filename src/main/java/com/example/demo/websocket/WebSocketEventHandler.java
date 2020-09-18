package com.example.demo.websocket;

import com.example.demo.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
public class WebSocketEventHandler {
    @Autowired
    private SimpMessageSendingOperations messageTemplate;
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event){
      log.info ("received a connection");
    }
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        Integer groupId = (Integer) headerAccessor.getSessionAttributes ().get ("groupId");
        if(username != null) {
            log.info("User Disconnected : " + username);
            Message chatMessage = new Message ();
            chatMessage.setMessageType (Message.MessageType.LEAVE);
            chatMessage.setSender(username);
            messageTemplate.convertAndSend("/topic/public/"+groupId, chatMessage);
        }
    }
}
