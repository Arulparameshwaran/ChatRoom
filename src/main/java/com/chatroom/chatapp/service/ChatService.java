package com.chatroom.chatapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chatroom.chatapp.model.ChatMessage;

@Service
public class ChatService {

    private final List<ChatMessage> messages = new ArrayList<>();

    public void saveMessage(ChatMessage message) {
        messages.add(message);
    }

    public List<ChatMessage> getAllMessages() {
        return messages;
    }
}
