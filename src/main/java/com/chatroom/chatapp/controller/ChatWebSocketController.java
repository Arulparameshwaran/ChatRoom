package com.chatroom.chatapp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.chatroom.chatapp.model.ChatMessageEntity;
import com.chatroom.chatapp.model.ChatMessageWS;
import com.chatroom.chatapp.repository.ChatMessageRepository;
import com.chatroom.chatapp.service.SentimentService;

@Controller
public class ChatWebSocketController {

    private final ChatMessageRepository repository;
    private final SentimentService sentimentService;

    // ✅ Inject SentimentService
    public ChatWebSocketController(ChatMessageRepository repository,
                                   SentimentService sentimentService) {
        this.repository = repository;
        this.sentimentService = sentimentService;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessageWS sendMessage(ChatMessageWS message) {

        // 🔥 CALL PYTHON NLP
        String sentiment =
                sentimentService.analyzeSentiment(message.getContent());

        // 🔥 STORE IN DB
        ChatMessageEntity entity = new ChatMessageEntity();
        entity.setSender(message.getSender());
        entity.setContent(message.getContent());
        entity.setSentiment(sentiment);   // ✅ ADD THIS

        repository.save(entity);

        // 🔥 SEND BACK TO UI
        message.setSentiment(sentiment);

        System.out.println(
            "WS Message: " + message.getContent() +
            " | Sentiment: " + sentiment
        );

        return message;
    }

    @MessageMapping("/typing")
    @SendTo("/topic/typing")
    public String typing(String username) {
        return username;
    }
}
