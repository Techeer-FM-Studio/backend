package com.techeer.fmstudio.domain.banner.dto;

import com.techeer.fmstudio.domain.banner.domain.Banner;
import org.springframework.stereotype.Component;

@Component
public class BannerMapper {
    public BannerInfo toInfo(Banner banner) {
        return BannerInfo.builder()
                .id(banner.getId())
                .ownerId(banner.getMember().getId())
                .title(banner.getTitle())
                .memo(banner.getMemo())
                .startAt(banner.getStartAt())
                .endAt(banner.getEndAt())
                .isFinished(banner.isFinished())
                .isOpened(banner.isOpened())
                .commentList(banner.getCommentList())
                .imageUrl(banner.getImageUrl())
                .likeCnt(banner.getLikeCnt())
                .build();
    }
}
