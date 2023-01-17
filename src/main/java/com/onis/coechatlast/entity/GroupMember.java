package com.onis.coechatlast.entity;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "group_member")
public class GroupMember {
    @Id
    @Column(name = "group_member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @Column(name = "joined_datetime")
    private Date joinedDatetime;

    @Column(name = "left_datetime")
    private Date leftDatetime;

    public GroupMember() {
    }

    public GroupMember(int groupMemberId, Contact contact, Conversation conversation, Date joinedDatetime, Date leftDatetime) {
        this.groupMemberId = groupMemberId;
        this.contact = contact;
        this.conversation = conversation;
        this.joinedDatetime = joinedDatetime;
        this.leftDatetime = leftDatetime;
    }

    public int getGroupMemberId() {
        return groupMemberId;
    }

    public void setGroupMemberId(int groupMemberId) {
        this.groupMemberId = groupMemberId;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Date getJoinedDatetime() {
        return joinedDatetime;
    }

    public void setJoinedDatetime(Date joinedDatetime) {
        this.joinedDatetime = joinedDatetime;
    }

    public Date getLeftDatetime() {
        return leftDatetime;
    }

    public void setLeftDatetime(Date leftDatetime) {
        this.leftDatetime = leftDatetime;
    }
}
