package com.techeer.fmstudio.domain.member.dao;

import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    @Query("select m from MemberEntity m where m.nickname = :nickname and m.isActive = true")
    Optional<MemberEntity> findMemberEntityByNickname(String nickname);
}
