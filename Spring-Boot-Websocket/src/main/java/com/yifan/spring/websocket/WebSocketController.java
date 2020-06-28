package com.yifan.spring.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    // 客户端请求/app/chat.sendMessage时，会被路由到这个方法
    // 消息会被发送给所有订阅了/topic/public的客户端
    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public WebSocketMessage sendMessage(@Payload WebSocketMessage message) {
        WebSocketMessage messageToReturn = new WebSocketMessage("This is the message returned from the server. Content: " + message.getContent());
        return messageToReturn;
    }

}
