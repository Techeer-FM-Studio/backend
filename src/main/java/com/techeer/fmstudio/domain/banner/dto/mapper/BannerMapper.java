package com.techeer.fmstudio.domain.banner.dto.mapper;

import com.techeer.fmstudio.domain.banner.domain.BannerEntity;
import com.techeer.fmstudio.domain.banner.dto.response.BannerInfo;
import com.techeer.fmstudio.domain.banner.dto.response.BannerPageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BannerMapper {
    public BannerInfo toBannerInfo(BannerEntity banner) {


        return BannerInfo.builder()
                .type(banner.getBannerType())
                .id(banner.getId())
                .writer(banner.getMember().getNickname())
                .title(banner.getTitle())
                .memo(banner.getMemo())
                .startAt(banner.getStartAt())
                .endAt(banner.getEndAt())
                .isFinished(banner.getIsFinished())
                .imageUrl(banner.getImageUrl())
                .likeCnt(banner.getLikeCnt())
                .readCnt(banner.getReadCnt())
                .build();
    }

    public BannerPageInfo mapEntityToBannerPageInfo(Page<BannerEntity> bannerEntityPage, int page, int size){
        List<BannerInfo> bannerInfoList =
                bannerEntityPage.stream().map(this::toBannerInfo).toList();
        return BannerPageInfo.builder()
                .totalPages(bannerEntityPage.getTotalPages())
                .totalElements(bannerEntityPage.getTotalElements())
                .size(size)
                .page(page)
                .content(bannerInfoList)
                .build();
    }
}
