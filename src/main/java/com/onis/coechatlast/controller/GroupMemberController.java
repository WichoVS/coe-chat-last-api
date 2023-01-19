package com.onis.coechatlast.controller;

import com.onis.coechatlast.DTOs.ConversationDTO;
import com.onis.coechatlast.DTOs.GroupMemberDTO;
import com.onis.coechatlast.entity.Contact;
import com.onis.coechatlast.entity.Conversation;
import com.onis.coechatlast.entity.GroupMember;
import com.onis.coechatlast.repository.ContactRepository;
import com.onis.coechatlast.repository.ConversationRepository;
import com.onis.coechatlast.repository.GroupMemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/group-member")
@CrossOrigin
public class GroupMemberController {
    private final GroupMemberRepository groupMemberRepository;
    private final ContactRepository contactRepository;
    private final ConversationRepository conversationRepository;

    public GroupMemberController(GroupMemberRepository groupMemberRepository, ContactRepository contactRepository, ConversationRepository conversationRepository) {
        this.groupMemberRepository = groupMemberRepository;
        this.contactRepository = contactRepository;
        this.conversationRepository = conversationRepository;
    }

    @GetMapping("")
    public List<GroupMemberDTO> getAllGroupMembers() {
        List<GroupMember> entities = groupMemberRepository.findAll();
        List<GroupMemberDTO> dtos = new ArrayList<>();

        for (GroupMember entity : entities) {
            dtos.add(new GroupMemberDTO(entity));
        }

        return dtos;
    }

    @GetMapping("/{groupMemberId}")
    public GroupMemberDTO getGroupMemberById(@PathVariable("groupMemberId") int groupMemberId) {
        GroupMember entity = groupMemberRepository.findById(groupMemberId).orElse(null);
        if (entity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group member not found!");
        }

        return new GroupMemberDTO(entity);
    }

    @PostMapping("")
    public GroupMemberDTO saveGroupMember(@RequestBody GroupMemberDTO dto) {
        if (dto.getContact().getContactId() == 0 && dto.getConversation().getConversationId() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contact and Conversation must have an Id!");

        Contact contact = contactRepository.findById(dto.getContact().getContactId()).orElse(null);
        Conversation conversation = conversationRepository.findById(dto.getConversation().getConversationId()).orElse(null);

        if (contact == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found!");

        if (conversation == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found!");

        GroupMember groupMember = new GroupMember();

        groupMember.setContact(contact);
        groupMember.setConversation(conversation);
        groupMember.setJoinedDatetime(new Date());
        groupMemberRepository.save(groupMember);

        return new GroupMemberDTO(groupMember);
    }

    @PutMapping("/{groupMemberId}")
    public GroupMemberDTO updateGroupMember(@PathVariable int groupMemberId) {
        if (groupMemberId == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group Member Id cannot be 0");

        GroupMember groupMember = groupMemberRepository.findById(groupMemberId).orElse(null);
        if (groupMember == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group Member not found!");

        groupMember.setLeftDatetime(new Date());
        groupMemberRepository.save(groupMember);

        return new GroupMemberDTO(groupMember);
    }

    @DeleteMapping("/{groupMemberId}")
    public Boolean deleteGroupMember(@PathVariable("groupMemberId") int groupMemberId) {
        if (groupMemberId == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group Member Id cannot be 0");

        GroupMember groupMember = groupMemberRepository.findById(groupMemberId).orElse(null);
        if (groupMember == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group Member not found!");

        groupMemberRepository.delete(groupMember);
        return true;
    }

    @GetMapping("/conversation/{idContact}/{idMember}")
    public int getChatBetweenMembers(@PathVariable("idContact") int idContact,
                                     @PathVariable("idMember") int idMember) {

        int idConversation = groupMemberRepository.conversationBetweenContactsExists(idContact, idMember);
        return idConversation;
    }

    @PostMapping("/chat")
    public int createChat(@RequestBody List<GroupMemberDTO> dtos) {
        //comparar conversaciones, que sean iguales
        ConversationDTO creatorConv = dtos.get(0).getConversation();
        boolean isSameConversation = true;
        List<Contact> members = new ArrayList<>();
        for (GroupMemberDTO dto : dtos
        ) {
            Contact newMember = contactRepository.findById(dto.getContact().getContactId()).orElse(null);
            if (newMember == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "One of the Members was not found!");
            dto.setConversation(creatorConv);
            members.add(newMember);
        }
        if (!isSameConversation)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Conversation must be the same for all the group members!");

        //Crear conversacion como todas son iguales. y guardar su ID
        Conversation conversation = new Conversation(dtos.get(0).getConversation());
        conversationRepository.save(conversation);
        for (Contact contact : members
        ) {
            GroupMember member = new GroupMember();
            member.setConversation(conversation);
            member.setJoinedDatetime(new Date());
            member.setContact(contact);
            groupMemberRepository.save(member);
        }

        return conversation.getConversationId();
    }


}
