package com.onis.coechatlast.controller;

import com.onis.coechatlast.DTOs.ConversationDTO;
import com.onis.coechatlast.entity.Conversation;
import com.onis.coechatlast.entity.GroupMember;
import com.onis.coechatlast.repository.ConversationRepository;
import com.onis.coechatlast.repository.GroupMemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/conversation")
@CrossOrigin
public class ConversationController {
    private final ConversationRepository conversationRepository;
    private final GroupMemberRepository groupMemberRepository;

    public ConversationController(ConversationRepository conversationRepository,
                                  GroupMemberRepository groupMemberRepository) {
        this.conversationRepository = conversationRepository;
        this.groupMemberRepository = groupMemberRepository;
    }

    @GetMapping("")
    public List<ConversationDTO> getAllConversations() {
        List<Conversation> conversations = conversationRepository.findAll();
        List<ConversationDTO> dtos = new ArrayList<>();
        for (Conversation conversation : conversations
        ) {
            dtos.add(new ConversationDTO(conversation));
        }

        return dtos;
    }

    @GetMapping("/{idConversation}")
    public ConversationDTO getConversationById(@PathVariable("idConversation") int idConversation) {
        Conversation conversation = conversationRepository.findById(idConversation).orElse(null);

        if (conversation == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation doesn't exists!");

        return new ConversationDTO(conversation);
    }

    @PostMapping("")
    public ConversationDTO createConversation(@RequestBody ConversationDTO conversationDTO) {
        if (conversationDTO.getConversationName().isEmpty() && conversationDTO.getConversationId() != 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conversation must have a name and Id should be 0!");
        Conversation conversation = new Conversation(conversationDTO);
        conversationRepository.save(conversation);

        return new ConversationDTO(conversation);
    }

    @PutMapping("")
    public ConversationDTO updateConversation(@RequestBody ConversationDTO conversationDTO) {
        if (conversationDTO.getConversationName().isEmpty() && conversationDTO.getConversationId() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conversation must have a name and an Id!");

        Conversation conversation = conversationRepository.findById(conversationDTO.getConversationId()).
                orElse(null);

        if (conversation == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation doesn't exists!");

        conversation.setConversationName(conversationDTO.getConversationName());
        return new ConversationDTO(conversation);
    }

    @DeleteMapping("/{idConversation}")
    public Boolean deleteConversation(@PathVariable("idConversation") int idConversation) {
        Conversation conversation = conversationRepository.findById(idConversation).orElse(null);
        if (conversation == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation doesn't exists!");

        conversationRepository.delete(conversation);
        return true;
    }

    @GetMapping("/contact/{idContact}")
    public List<ConversationDTO> getConversationsByContact(@PathVariable("idContact") int idContact) {
        if (idContact == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contact Id must be more than 0");

        List<GroupMember> groupMemberList = groupMemberRepository.getGroupMembersByContact_ContactId(idContact);

        List<Conversation> conversations = new ArrayList<>();
        List<ConversationDTO> dtos = new ArrayList<>();

        for (GroupMember gp : groupMemberList
        ) {
            conversations.add(gp.getConversation());
        }

        for (Conversation entity : conversations
        ) {
            dtos.add(new ConversationDTO(entity));
        }

        return dtos;
    }
}
