//package com.techeer.fmstudio.domain.task.dao;
//
//import com.techeer.fmstudio.domain.task.domain.TestMember;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.Optional;
//
//public interface TestMemberRepository extends JpaRepository<TestMember, Long> {
//    @Query("select t from TestMember t where t.nickname = :nickname and t.isActive = true")
//    Optional<TestMember> findTestMemberByNickname(String nickname);
//}