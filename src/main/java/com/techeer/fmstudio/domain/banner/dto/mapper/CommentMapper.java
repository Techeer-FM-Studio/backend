package com.techeer.fmstudio.domain.banner.dto.mapper;

import com.techeer.fmstudio.domain.banner.domain.CommentEntity;
import com.techeer.fmstudio.domain.banner.dto.response.CommentInfo;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentInfo toInfo(CommentEntity entity){
        return CommentInfo.builder()
                .id(entity.getId())
                .writer(entity.getWriter().getNickname())
                .comments(entity.getContents())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
