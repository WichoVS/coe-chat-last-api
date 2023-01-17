package com.onis.coechatlast.DTOs;

import com.onis.coechatlast.entity.Contact;
import com.onis.coechatlast.entity.GroupMember;
import com.onis.coechatlast.entity.Message;
import jakarta.persistence.*;

import java.util.List;

public class ContactDTO {
    private int contactId;
    private String firstName;
    private String lastName;
    private byte[] profilePhoto;
    private long phoneNumber;
    private String status;

    public ContactDTO() {

    }

    public ContactDTO(int contactId, String firstName, String lastName, byte[] profilePhoto, long phoneNumber, String status) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePhoto = profilePhoto;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public ContactDTO(Contact entity) {
        this.contactId = entity.getContactId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.profilePhoto = entity.getProfilePhoto();
        this.phoneNumber = entity.getPhoneNumber();
        this.status = entity.getStatus();
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
}
