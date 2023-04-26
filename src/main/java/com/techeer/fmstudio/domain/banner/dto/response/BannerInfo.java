package com.techeer.fmstudio.domain.banner.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techeer.fmstudio.domain.banner.domain.BannerType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BannerInfo {

    private BannerType type;

    private Long id;

    private String writer;

    private String title;

    private String memo;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endAt;

    private List<String> imageUrl;

    private Integer likeCnt;

    private Boolean isFinished;

    private Boolean isLiked;

    private Boolean isIncluded;

    private Integer readCnt;
}
