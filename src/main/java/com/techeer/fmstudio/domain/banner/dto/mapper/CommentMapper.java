package com.techeer.fmstudio.domain.banner.dto.mapper;

import com.techeer.fmstudio.domain.banner.domain.CommentEntity;
import com.techeer.fmstudio.domain.banner.dto.response.CommentInfo;
import com.techeer.fmstudio.domain.banner.dto.response.CommentPageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentMapper {
    public CommentInfo toCommentInfo(CommentEntity entity){
        return CommentInfo.builder()
                .id(entity.getId())
                .writer(entity.getWriter().getNickname())
                .comments(entity.getContents())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public CommentPageInfo mapEntityToCommentPageInfo(Page<CommentEntity> commentEntityPage, int page, int size){
        List<CommentInfo> commentInfoList =
                commentEntityPage.stream().map(this::toCommentInfo).toList();
        return CommentPageInfo.builder()
                .totalPages(commentEntityPage.getTotalPages())
                .totalElements(commentEntityPage.getTotalElements())
                .size(size)
                .page(page)
                .content(commentInfoList)
                .build();
    }
}
