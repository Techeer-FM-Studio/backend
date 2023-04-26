package com.techeer.fmstudio.domain.banner.dto.mapper;

import com.techeer.fmstudio.domain.banner.domain.BannerEntity;
import com.techeer.fmstudio.domain.banner.dto.response.MyBannerInfo;
import org.springframework.stereotype.Component;


@Component
public class MyBannerMapper {
    public MyBannerInfo toMyBannerInfo(BannerEntity banner) {
        return MyBannerInfo.builder()
                .type(banner.getBannerType().name())
                .bannerId(banner.getId())
                .title(banner.getTitle())
                .memo(banner.getMemo())
                .startAt(banner.getStartAt())
                .endAt(banner.getEndAt())
                .imageUrl(banner.getImageUrl())
                .isFinished(banner.getIsFinished())
                .build();
    }
}
