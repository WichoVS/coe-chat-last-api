package com.onis.coechatlast.repository;

import com.onis.coechatlast.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> getMessagesByConversation_ConversationId(int idConversation);
}
