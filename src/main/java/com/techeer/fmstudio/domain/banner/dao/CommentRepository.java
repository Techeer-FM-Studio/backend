package com.techeer.fmstudio.domain.banner.dao;

import com.techeer.fmstudio.domain.banner.domain.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
