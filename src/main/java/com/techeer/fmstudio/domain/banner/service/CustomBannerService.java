package com.techeer.fmstudio.domain.banner.service;

import com.techeer.fmstudio.domain.banner.dao.BannerRepository;
import com.techeer.fmstudio.domain.banner.dao.MyBannerListRepository;
import com.techeer.fmstudio.domain.banner.domain.BannerEntity;
import com.techeer.fmstudio.domain.banner.domain.BannerType;
import com.techeer.fmstudio.domain.banner.domain.MyBannerList;
import com.techeer.fmstudio.domain.banner.dto.request.CustomBannerAddMyBannerRequest;
import com.techeer.fmstudio.domain.banner.dto.request.CustomBannerCreateRequest;
import com.techeer.fmstudio.domain.banner.exception.NotFoundBannerException;
import com.techeer.fmstudio.domain.member.dao.MemberRepository;
import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.domain.member.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomBannerService {

    private final BannerRepository bannerRepository;
    private final MemberRepository memberRepository;

    private final MyBannerListRepository myBannerListRepository;

    public BannerEntity create(CustomBannerCreateRequest request) {

        MemberEntity member = memberRepository
                .findMemberEntityByNickname(request.getNickname())
                .orElseThrow(NotFoundMemberException::new);

        BannerEntity newBanner = BannerEntity.builder()
                .bannerType(BannerType.CUSTOM)
                .member(member)
                .title(request.getTitle())
                .memo(request.getMemo())
                .startAt(request.getStartedAt())
                .endAt(request.getEndAt())
                .build();

        if(!request.getImageUrl().isEmpty()){
            newBanner.addImageUrl(request.getImageUrl());
        }

        return bannerRepository.save(newBanner);
    }

    public BannerEntity delete(Long id) {

        BannerEntity foundBanner = bannerRepository
                .findById(id)
                .orElseThrow(NotFoundBannerException::new);

        foundBanner.deleteBanner();

        return foundBanner;
    }

    public MyBannerList addMyBanner(CustomBannerAddMyBannerRequest request, Long bannerId){
        MemberEntity member = memberRepository.findMemberEntityByNickname(request.getNickname()).orElseThrow(NotFoundMemberException::new);
        BannerEntity banner = bannerRepository.findById(bannerId).orElseThrow(NotFoundBannerException::new);
        MyBannerList myBannerList = MyBannerList.builder()
                .banner(banner)
                .member(member)
                .build();
        return myBannerListRepository.save(myBannerList);
    }
}
