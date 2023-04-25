package com.techeer.fmstudio.domain.banner.dto;

import com.techeer.fmstudio.domain.banner.domain.BannerEntity;
import org.springframework.stereotype.Component;

@Component
public class BannerMapper {
    public BannerInfo toInfo(BannerEntity banner) {
        return BannerInfo.builder()
                .type(banner.getBannerType())
                .bannerId(banner.getId())
                .nickname(banner.getMember().getNickname())
                .title(banner.getTitle())
                .memo(banner.getMemo())
                .startAt(banner.getStartAt())
                .endAt(banner.getEndAt())
                .isFinished(banner.isFinished())
                .commentList(banner.getCommentList())
                .imageUrl(banner.getImageUrl())
                .likeCnt(banner.getLikeCnt())
                .readCnt(banner.getReadCnt())
                .build();
    }
}
