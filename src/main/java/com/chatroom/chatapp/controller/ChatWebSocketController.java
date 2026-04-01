package com.chatroom.chatapp.controller;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


import com.chatroom.chatapp.model.ChatMessageEntity;
import com.chatroom.chatapp.model.ChatMessageWS;
import com.chatroom.chatapp.repository.ChatMessageRepository;
import com.chatroom.chatapp.service.SentimentService;
import com.chatroom.chatapp.service.OnlineUserService;

@Controller
public class ChatWebSocketController {

    private final ChatMessageRepository repository;
    private final SentimentService sentimentService;
    private final OnlineUserService onlineUserService;
    private final Set<String> onlineUsers = ConcurrentHashMap.newKeySet();

    // Inject SentimentService and OnlineUserService
    public ChatWebSocketController(ChatMessageRepository repository,
                               SentimentService sentimentService,
                               OnlineUserService onlineUserService) {

    this.repository = repository;
    this.sentimentService = sentimentService;
    this.onlineUserService = onlineUserService;
}
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessageWS sendMessage(ChatMessageWS message) {

        //CALL PYTHON NLP
        String sentiment =
                sentimentService.analyzeSentiment(message.getContent());

        //STORE IN DB
        ChatMessageEntity entity = new ChatMessageEntity();
        entity.setSender(message.getSender());
        entity.setContent(message.getContent());
        entity.setEmotion(sentiment);   // ✅ ADD THIS

        repository.save(entity);

        //SEND BACK TO UI
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
    @MessageMapping("/userJoin")
    @SendTo("/topic/onlineUsers")
    public Set<String> userJoin(String username) {
    
        onlineUsers.add(username);
        return onlineUsers;
    }
    @MessageMapping("/userLeave")
    @SendTo("/topic/onlineUsers")
    public Set<String> userLeave(String username) {
    
        onlineUsers.remove(username);
        return onlineUsers;
    }
@MessageMapping("/onlineUsers")
@SendTo("/topic/onlineUsers")
public Set<String> getOnlineUsers() {
    return onlineUsers;
}
}
