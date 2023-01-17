package com.onis.coechatlast.entity;

import com.onis.coechatlast.DTOs.ContactDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "profile_photo")
    private byte[] profilePhoto;
    @Column(name = "phone_number")
    private long phoneNumber;
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "contact")
    private List<GroupMember> groupMemberList;
    @OneToMany(mappedBy = "contact")
    private List<Message> messageEntityList;

    public Contact() {
    }

    public Contact(int contactId, String firstName, String lastName, byte[] profilePhoto, long phoneNumber, String status) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePhoto = profilePhoto;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public Contact(ContactDTO dto) {
        this.contactId = dto.getContactId();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.profilePhoto = dto.getProfilePhoto();
        this.phoneNumber = dto.getPhoneNumber();
        this.status = dto.getStatus();
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GroupMember> getGroupMemberList() {
        return groupMemberList;
    }

    public void setGroupMemberList(List<GroupMember> groupMemberList) {
        this.groupMemberList = groupMemberList;
    }

    public List<Message> getMessageEntityList() {
        return messageEntityList;
    }

    public void setMessageEntityList(List<Message> messageEntityList) {
        this.messageEntityList = messageEntityList;
    }

    public void updateContact(ContactDTO dto) {
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.profilePhoto = dto.getProfilePhoto();
        this.phoneNumber = dto.getPhoneNumber();
    }
}
