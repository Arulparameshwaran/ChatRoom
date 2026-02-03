package com.chatroom.chatapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.chatroom.chatapp.model.ChatMessageEntity;
import com.chatroom.chatapp.repository.ChatMessageRepository;

@RestController
@RequestMapping("/chat")
public class ChatHistoryController {

    private final ChatMessageRepository repository;

    public ChatHistoryController(ChatMessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/history")
    public List<ChatMessageEntity> getHistory() {
        return repository.findLast50Messages();
    }
}
