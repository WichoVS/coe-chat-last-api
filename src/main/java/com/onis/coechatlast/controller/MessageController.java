package com.onis.coechatlast.controller;

import com.onis.coechatlast.DTOs.MessageDTO;
import com.onis.coechatlast.entity.Contact;
import com.onis.coechatlast.entity.Conversation;
import com.onis.coechatlast.entity.Message;
import com.onis.coechatlast.repository.ContactRepository;
import com.onis.coechatlast.repository.ConversationRepository;
import com.onis.coechatlast.repository.MessageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/message")
@CrossOrigin
public class MessageController {
    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final ContactRepository contactRepository;

    public MessageController(MessageRepository messageRepository,
                             ConversationRepository conversationRepository,
                             ContactRepository contactRepository) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.contactRepository = contactRepository;
    }

    @GetMapping("")
    public List<MessageDTO> getAllMessages() {
        return null;
    }

    @GetMapping("/conversation/{idConversation}")
    public List<MessageDTO> getMessagesByConversation(@PathVariable("idConversation") int idConversation) {
        if (idConversation == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conversation Id must be more than 0!");

        List<Message> messages = messageRepository.getMessagesByConversation_ConversationId(idConversation);
        List<MessageDTO> dtos = new ArrayList<>();

        for (Message entity : messages
        ) {
            dtos.add(new MessageDTO(entity));
        }

        return dtos;
    }

    @GetMapping("/{idMessage}")
    public MessageDTO getMessageById(@PathVariable("idMessage") int idMessage) {
        if (idMessage == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message Id must be more than 0!");

        Message message = messageRepository.findById(idMessage).orElse(null);

        if (message == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found!");

        return new MessageDTO(message);
    }

    @PostMapping("")
    public MessageDTO saveMessage(@RequestBody MessageDTO dto) {
        if (dto.getConversation().getConversationId() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message must contain a Conversation Id!");

        if (dto.getContact().getContactId() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message must contain a Contact Id!");

        if (dto.getMessageText().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message text cannot be empty!");

        Conversation conversation = conversationRepository.findById(dto.getConversation().getConversationId())
                .orElse(null);
        if (conversation == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found!");

        Contact contact = contactRepository.findById(dto.getContact().getContactId()).orElse(null);
        if (contact == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found!");

        Message message = new Message();
        message.setConversation(conversation);
        message.setContact(contact);
        message.setMessageText(dto.getMessageText());
        message.setSentDatetime(new Date());

        messageRepository.save(message);
        return new MessageDTO(message);
    }

    @PutMapping("")
    public MessageDTO updateMessage(@RequestBody MessageDTO dto) {
        if (dto.getMessageId() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message Id must be more than 0!");
        if (dto.getMessageText().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message text cannot be empty!");

        Message message = messageRepository.findById(dto.getMessageId()).orElse(null);
        if (message == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found!");

        message.setMessageText(dto.getMessageText());

        messageRepository.save(message);

        return new MessageDTO(message);
    }

    @DeleteMapping("/{idMessage}")
    public Boolean deleteMessage(@PathVariable("idMessage") int idMessage) {
        if (idMessage == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message Id must be more than 0!");

        Message message = messageRepository.findById(idMessage).orElse(null);
        if (message == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found!");

        messageRepository.delete(message);
        return true;
    }
}
