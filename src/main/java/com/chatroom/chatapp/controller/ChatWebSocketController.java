package com.chatroom.chatapp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.chatroom.chatapp.model.ChatMessageEntity;
import com.chatroom.chatapp.model.ChatMessageWS;
import com.chatroom.chatapp.repository.ChatMessageRepository;

@Controller
public class ChatWebSocketController {

    private final ChatMessageRepository repository;

    public ChatWebSocketController(ChatMessageRepository repository) {
        this.repository = repository;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessageWS sendMessage(ChatMessageWS message) {

        ChatMessageEntity entity = new ChatMessageEntity();
        entity.setSender(message.getSender());
        entity.setContent(message.getContent());

        repository.save(entity); // 🔥 STORE IN DB

        return message; // broadcast
    }
    @MessageMapping("/typing")
    @SendTo("/topic/typing")
    public String typing(String username) {
        return username;
    }

}
