package com.chatroom.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chatroom.chatapp.model.ChatMessageEntity;

public interface ChatMessageRepository
        extends JpaRepository<ChatMessageEntity, Long> {

    @Query(value = "SELECT * FROM chat_message ORDER BY timestamp DESC LIMIT 50",
           nativeQuery = true)
    List<ChatMessageEntity> findLast50Messages();
}
