package com.onis.coechatlast.repository;

import com.onis.coechatlast.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Integer> {
    List<GroupMember> getGroupMembersByContact_ContactId(int idContact);
    @Procedure(procedureName = "sp_conversationBetweenContactsExists", outputParameterName = "existsConversation")
    int conversationBetweenContactsExists(@Param("contactCreator") int contactCreator, @Param("contactMember") int contactMember);
}
