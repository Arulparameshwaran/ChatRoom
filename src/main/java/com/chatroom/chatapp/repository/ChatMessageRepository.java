package com.chatroom.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chatroom.chatapp.model.ChatMessageEntity;

public interface ChatMessageRepository
        extends JpaRepository<ChatMessageEntity, Long> {
}
