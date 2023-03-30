package com.techeer.fmstudio.domain.task.dao;

import com.techeer.fmstudio.domain.task.domain.TestMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestMemberRepository extends JpaRepository<TestMember, Long> {
}