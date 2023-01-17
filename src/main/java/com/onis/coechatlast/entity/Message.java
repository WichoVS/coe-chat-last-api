package com.onis.coechatlast.entity;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private int messageId;

    @ManyToOne
    @JoinColumn(name = "from_contact")
    private Contact contact;

    @Column(name = "message_text")
    private String messageText;

    @Column(name = "sent_datetime")
    private Date sentDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;


    public Message() {
    }

    public Message(int messageId, Contact contact, String messageText, Date sentDatetime, Conversation conversation) {
        this.messageId = messageId;
        this.contact = contact;
        this.messageText = messageText;
        this.sentDatetime = sentDatetime;
        this.conversation = conversation;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
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

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}
