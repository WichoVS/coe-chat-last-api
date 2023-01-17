package com.onis.coechatlast.repository;

import com.onis.coechatlast.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Contact getContactByPhoneNumber(long phoneNumber);
}
