package com.onis.coechatlast.entity;

import com.onis.coechatlast.DTOs.ConversationDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "conversation")
public class Conversation {
    @Id
    @Column(name = "conversation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int conversationId;

    @Column(name = "conversation_name")
    private String conversationName;

    @OneToMany(mappedBy = "conversation")
    private List<GroupMember> groupMemberList;

    @OneToMany(mappedBy = "conversation")
    private List<Message> messages;

    public Conversation() {
    }

    public Conversation(int conversationId, String conversationName, List<GroupMember> groupMemberList, List<Message> messages) {
        this.conversationId = conversationId;
        this.conversationName = conversationName;
        this.groupMemberList = groupMemberList;
        this.messages = messages;
    }

    public Conversation(ConversationDTO dto) {
        this.conversationId = dto.getConversationId();
        this.conversationName = dto.getConversationName();
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

    public List<GroupMember> getGroupMemberList() {
        return groupMemberList;
    }

    public void setGroupMemberList(List<GroupMember> groupMemberList) {
        this.groupMemberList = groupMemberList;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
