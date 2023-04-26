package com.techeer.fmstudio.domain.banner.dao;

import com.techeer.fmstudio.domain.banner.domain.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("SELECT c FROM CommentEntity c WHERE c.banner.id = :bannerId " +
            "AND c.isActive = true " +
            "ORDER BY c.createdAt asc")
    Page<CommentEntity> findCommentEntityByBannerIdWithPagination(
            Pageable pageable,
            @Param("bannerId") Long bannerId);
}
