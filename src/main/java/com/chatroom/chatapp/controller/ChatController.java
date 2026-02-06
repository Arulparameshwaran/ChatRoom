package com.chatroom.chatapp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chatroom.chatapp.model.ChatMessage;
import com.chatroom.chatapp.service.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody ChatMessage message) {

        message.setTimestamp(LocalDateTime.now().toString());

        

        chatService.saveMessage(message);
        return "Message sent";
    }

    @GetMapping("/messages")
    public List<ChatMessage> getMessages() {
        return chatService.getAllMessages();
    }
}
