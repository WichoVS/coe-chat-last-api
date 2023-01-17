package com.onis.coechatlast.DTOs;

import com.onis.coechatlast.entity.Contact;
import com.onis.coechatlast.entity.Conversation;
import com.onis.coechatlast.entity.GroupMember;
import jakarta.persistence.*;

import java.util.Date;

public class GroupMemberDTO {
    private int groupMemberId;
    private ContactDTO contact;
    private ConversationDTO conversation;
    private Date joinedDatetime;
    private Date leftDatetime;

    public GroupMemberDTO(){

    }

    public GroupMemberDTO(int groupMemberId, ContactDTO contact, ConversationDTO conversation, Date joinedDatetime, Date leftDatetime) {
        this.groupMemberId = groupMemberId;
        this.contact = contact;
        this.conversation =  conversation;
        this.joinedDatetime = joinedDatetime;
        this.leftDatetime = leftDatetime;
    }

    public GroupMemberDTO(GroupMember entity){
        this.groupMemberId = entity.getGroupMemberId();
        this.contact = new ContactDTO(entity.getContact());
        this.conversation = new ConversationDTO(entity.getConversation());
        this.joinedDatetime = entity.getJoinedDatetime();
        this.leftDatetime = entity.getLeftDatetime();
    }

    public int getGroupMemberId() {
        return groupMemberId;
    }

    public void setGroupMemberId(int groupMemberId) {
        this.groupMemberId = groupMemberId;
    }

    public ContactDTO getContact() {
        return contact;
    }

    public void setContact(ContactDTO contact) {
        this.contact = contact;
    }

    public ConversationDTO getConversation() {
        return conversation;
    }

    public void setConversation(ConversationDTO conversation) {
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
