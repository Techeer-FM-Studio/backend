package com.techeer.fmstudio.domain.task.dao;

import com.techeer.fmstudio.domain.task.domain.SharedMember;
import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.domain.TestMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SharedMemberRepository extends JpaRepository<SharedMember, Long> {
    @Query("select s from SharedMember s where s.id = :id and s.isActive = true")
    Optional<SharedMember> findById(Long id);

    @Query("select s from SharedMember s where s.id = :taskId and s.isActive = true")
    Optional<SharedMember> findSharedMemberByTask(Long taskId);

    @Query("select s from SharedMember s where s.isActive is true")
    Page<SharedMember> findSharedMembersWithPagination(Pageable pageable);
}
