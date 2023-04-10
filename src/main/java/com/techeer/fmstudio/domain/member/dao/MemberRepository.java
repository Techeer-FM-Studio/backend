package com.techeer.fmstudio.domain.member.dao;

import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
