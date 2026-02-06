package com.chatroom.chatapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatroom.chatapp.model.ChatMessage;

@Service
public class ChatService {

    private final List<ChatMessage> messages = new ArrayList<>();

    @Autowired
    private SentimentService sentimentService;

    public void saveMessage(ChatMessage message) {

        // ✅ CALL PYTHON SERVICE
        String sentiment =
                sentimentService.analyzeSentiment(message.getContent());

        // ✅ SET SENTIMENT
        message.setSentiment(sentiment);

        // ✅ SAVE MESSAGE
        messages.add(message);

        System.out.println(
            "Message: " + message.getContent() +
            " | Sentiment: " + sentiment
        );
    }

    public List<ChatMessage> getAllMessages() {
        return messages;
    }
}
