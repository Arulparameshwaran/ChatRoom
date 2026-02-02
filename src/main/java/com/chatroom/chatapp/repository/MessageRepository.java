package com.chatroom.chatapp.repository;

import com.chatroom.chatapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
