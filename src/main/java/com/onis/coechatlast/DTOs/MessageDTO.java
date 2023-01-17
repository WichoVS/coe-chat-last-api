package com.onis.coechatlast.DTOs;

import com.onis.coechatlast.entity.Contact;
import com.onis.coechatlast.entity.Conversation;
import com.onis.coechatlast.entity.Message;
import jakarta.persistence.*;

import java.util.Date;


public class MessageDTO {
    private int messageId;
    private ContactDTO contact;
    private String messageText;
    private Date sentDatetime;
    private ConversationDTO conversation;

    public MessageDTO() {
    }

    public MessageDTO(int messageId, ContactDTO contact, String messageText, Date sentDatetime, ConversationDTO conversation) {
        this.messageId = messageId;
        this.contact = contact;
        this.messageText = messageText;
        this.sentDatetime = sentDatetime;
        this.conversation = conversation;
    }

    public MessageDTO(Message entity){
        this.messageId = entity.getMessageId();
        this.contact = new ContactDTO(entity.getContact());
        this.messageText = entity.getMessageText();
        this.sentDatetime = entity.getSentDatetime();
        this.conversation = new ConversationDTO(entity.getConversation());
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public ContactDTO getContact() {
        return contact;
    }

    public void setContact(ContactDTO contact) {
        this.contact = contact;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getSentDatetime() {
        return sentDatetime;
    }

    public void setSentDatetime(Date sentDatetime) {
        this.sentDatetime = sentDatetime;
    }

    public ConversationDTO getConversation() {
        return conversation;
    }

    public void setConversation(ConversationDTO conversation) {
        this.conversation = conversation;
    }
}
