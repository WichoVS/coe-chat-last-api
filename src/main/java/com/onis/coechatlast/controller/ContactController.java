package com.onis.coechatlast.controller;

import com.onis.coechatlast.DTOs.ContactDTO;
import com.onis.coechatlast.entity.Contact;
import com.onis.coechatlast.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contact")
@CrossOrigin
public class ContactController {
    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping("")
    public ContactDTO saveContact(@RequestBody ContactDTO contact) {
        Contact dbContact = contactRepository.getContactByPhoneNumber(contact.getPhoneNumber());

        if (dbContact != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Contact already exists!");
        }

        Contact newContact = new Contact(contact);
        contactRepository.save(newContact);

        contact = new ContactDTO(newContact);
        return contact;
    }

    @PutMapping("")
    public ContactDTO updateContact(@RequestBody ContactDTO contact) {
        Contact contactDb = contactRepository.findById(contact.getContactId()).orElse(null);
        if (contactDb == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact doesn't exists!");
        }

        contactDb.updateContact(contact);
        contactRepository.save(contactDb);
        return new ContactDTO(contactDb);
    }

    @GetMapping("")
    public List<ContactDTO> getAllContacts() {
        List<ContactDTO> contactDTOS = new ArrayList<>();
        List<Contact> entities = contactRepository.findAll();

        for (Contact entity : entities
        ) {
            contactDTOS.add(new ContactDTO(entity));
        }
        return contactDTOS;
    }

    @GetMapping("/{idContact}")
    public ContactDTO getContactById(@PathVariable("idContact") int idContact) {
        Contact contact = contactRepository.findById(idContact).orElse(null);
        if (contact == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact doesn't exists!");
        }
        return new ContactDTO(contact);
    }

    @GetMapping("/phone-number/{phoneNumber}")
    public ContactDTO getContactByPhoneNumber(@PathVariable("phoneNumber") long phoneNumber) {
        Contact contact = contactRepository.getContactByPhoneNumber(phoneNumber);
        if (contact == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact doesn't exists!");
        }

        return new ContactDTO(contact);
    }

    @DeleteMapping("/{idContact}")
    public Boolean deleteContactById(@PathVariable("idContact") int idContact) {
        Contact contactToDel = contactRepository.findById(idContact).orElse(null);
        if (contactToDel == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This contact doesn't exists!");
        }

        contactRepository.delete(contactToDel);
        return true;
    }

    @PutMapping("/status/{idContact}/{status}")
    public ContactDTO updateStatus(@PathVariable("idContact") int idContact, @PathVariable("status") String status) {
        if (!status.toUpperCase().equals("ONLINE") && !status.toUpperCase().equals("OFFLINE"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status can only be ONLINE or OFFLINE!");

        if (idContact == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contact Id cannot be 0!");

        Contact contact = contactRepository.findById(idContact).orElse(null);
        if (contact == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found!");

        contact.setStatus(status.toUpperCase());
        contactRepository.save(contact);
        return new ContactDTO(contact);
    }
}
