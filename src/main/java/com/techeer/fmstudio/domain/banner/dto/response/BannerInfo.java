package com.techeer.fmstudio.domain.banner.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techeer.fmstudio.domain.banner.domain.BannerType;
import com.techeer.fmstudio.domain.banner.domain.CommentEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BannerInfo {

    private BannerType type;

    private Long bannerId;

    private String nickname;

    private String title;

    private String memo;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endAt;

    private List<CommentEntity> commentList;

    private List<String> imageUrl;

    private Integer likeCnt;

    private boolean isFinished;

    private boolean isLiked;

    private boolean isIncluded;

    private Integer readCnt;
}
