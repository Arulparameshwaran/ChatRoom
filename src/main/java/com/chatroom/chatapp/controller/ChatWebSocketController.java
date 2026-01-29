package com.chatroom.chatapp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.chatroom.chatapp.model.ChatMessageWS;

@Controller
public class ChatWebSocketController {

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessageWS sendMessage(ChatMessageWS message) {
        return message; // broadcast to all
    }
}
