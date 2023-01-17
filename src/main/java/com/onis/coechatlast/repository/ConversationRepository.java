package com.onis.coechatlast.repository;

import com.onis.coechatlast.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
}
