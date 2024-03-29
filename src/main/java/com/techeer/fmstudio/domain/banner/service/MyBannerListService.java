package com.techeer.fmstudio.domain.banner.service;


import com.techeer.fmstudio.domain.banner.dao.BannerRepository;
import com.techeer.fmstudio.domain.banner.dao.MyBannerListRepository;
import com.techeer.fmstudio.domain.banner.domain.BannerEntity;
import com.techeer.fmstudio.domain.banner.domain.MyBannerList;
import com.techeer.fmstudio.domain.banner.dto.mapper.MyBannerMapper;
import com.techeer.fmstudio.domain.banner.dto.request.CustomBannerAddMyBannerRequest;
import com.techeer.fmstudio.domain.banner.dto.request.MyBannerDeleteRequest;
import com.techeer.fmstudio.domain.banner.dto.response.MyBannerInfo;
import com.techeer.fmstudio.domain.banner.exception.NotFoundBannerException;
import com.techeer.fmstudio.domain.member.dao.MemberRepository;
import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.domain.member.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyBannerListService {
    private final MemberRepository memberRepository;
    private final MyBannerListRepository myBannerListRepository;
    private final BannerRepository bannerRepository;
    private final MyBannerMapper myBannerMapper;
    public MyBannerList addMyBanner(CustomBannerAddMyBannerRequest request, Long bannerId){
        MemberEntity member = memberRepository.findMemberEntityByNickname(request.getNickname()).orElseThrow(NotFoundMemberException::new);
        BannerEntity banner = bannerRepository.findById(bannerId).orElseThrow(NotFoundBannerException::new);
        MyBannerList myBannerList = MyBannerList.builder()
                .banner(banner)
                .member(member)
                .build();
        return myBannerListRepository.save(myBannerList);
    }

    public List<MyBannerInfo> getMyBannerWithPagination(String memberId, int year, int month) {
         List<BannerEntity> bannerEntityList = myBannerListRepository.findMyBannerByMemberIdAndYearAndMonth(memberId, year, month);
         return bannerEntityList.stream().map(myBannerMapper::toMyBannerInfo).toList();
    }

    public void deleteMyBanner(MyBannerDeleteRequest request, Long bannerId){
        MemberEntity member = memberRepository.findMemberEntityByNickname(request.getNickname())
                .orElseThrow(NotFoundMemberException::new);
        myBannerListRepository.deleteByMemberIdAndBannerId(member.getId(), bannerId);
    }
}
