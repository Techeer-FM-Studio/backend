package com.techeer.fmstudio.domain.banner.service;

import com.techeer.fmstudio.domain.banner.dao.BannerRepository;
import com.techeer.fmstudio.domain.banner.domain.Banner;
import com.techeer.fmstudio.domain.banner.dto.BannerCreateRequest;
import com.techeer.fmstudio.domain.member.dao.MemberRepository;
import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.domain.member.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;
    private final MemberRepository memberRepository;

    public Banner create(BannerCreateRequest request) {

        MemberEntity member = memberRepository
                .findById(request.getMemberId())
                .orElseThrow(NotFoundMemberException::new);

        Banner newBanner = Banner.builder()
                .member(member)
                .title(request.getTitle())
                .memo(request.getMemo())
                .startAt(request.getStartedAt())
                .endAt(request.getEndAt())
                .build();

        return bannerRepository.save(newBanner);
    }
}
