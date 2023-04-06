package com.techeer.fmstudio.domain.task.dao;

import com.techeer.fmstudio.domain.task.domain.SharedMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SharedMemberRepository extends JpaRepository<SharedMember, Long> {
    @Query("select s from SharedMember s where s.id = :id and s.isActive = true")
    Optional<SharedMember> findById(Long id);
}
