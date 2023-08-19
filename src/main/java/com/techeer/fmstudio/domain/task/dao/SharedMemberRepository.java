package com.techeer.fmstudio.domain.task.dao;

import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.domain.task.domain.SharedMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedMemberRepository extends JpaRepository<SharedMember, Long> {
    @Query("select s from SharedMember s where s.id = :id and s.isActive = true")
    Optional<SharedMember> findById(@Param("id") Long id);


    @Query("select s from SharedMember s join s.task where s.task.id = :taskId and s.isActive = true")
    Optional<SharedMember> findSharedMemberByTask(@Param("taskId") Long taskId);


    @Query("select s from SharedMember s where s.isActive is true")
    Page<SharedMember> findSharedMembersWithPagination(Pageable pageable);

//    @Query("select s from SharedMember s join fetch s.task join fetch s.memberEntity where s.memberEntity = :memberEntity and s.isActive = true")
    @Query("select s from SharedMember s where s.memberEntity = :memberEntity and s.isActive = true")
    List<SharedMember> findSharedMembersByMemberEntity(@Param("memberEntity") MemberEntity memberEntity);

//    @Query("select s from SharedMember s where s.memberEntity.nickname = :nickName")
    @Query("select s from SharedMember s join s.memberEntity where s.memberEntity.nickname = :nickName and s.isActive = true")
    List<SharedMember> findSharedMemberByMemberEntityNickName(@Param("nickName") String nickName);

    @Query("select s from SharedMember s where s.memberEntity.nickname = :nickName and s.isActive = true")
//    @Query("select s from SharedMember s join fetch s.memberEntity m where m.nickname = :nickName and s.isActive = true")
    List<SharedMember> findSharedMemberByMemberEntityNickName1(@Param("nickName") String nickName);

    @Query("select s.id from SharedMember s where s.memberEntity.nickname = :nickName and s.isActive = true")
    List<SharedMember> findSharedMembersByMemberEntityNickname(@Param("nickName") String nickName);
}
