package com.onis.coechatlast.DTOs;

import com.onis.coechatlast.entity.Conversation;
import com.onis.coechatlast.entity.GroupMember;
import com.onis.coechatlast.entity.Message;
import jakarta.persistence.*;

import java.util.List;

public class ConversationDTO {
    private int conversationId;
    private String conversationName;


    public ConversationDTO() {
    }

    public ConversationDTO(Conversation entity) {
        this.conversationId = entity.getConversationId();
        this.conversationName = entity.getConversationName();
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }
}
