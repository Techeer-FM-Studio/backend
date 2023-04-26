package com.techeer.fmstudio.domain.banner.service;


import com.techeer.fmstudio.domain.banner.dao.BannerRepository;
import com.techeer.fmstudio.domain.banner.dao.MyBannerListRepository;
import com.techeer.fmstudio.domain.banner.domain.BannerEntity;
import com.techeer.fmstudio.domain.banner.domain.MyBannerList;
import com.techeer.fmstudio.domain.banner.dto.mapper.BannerMapper;
import com.techeer.fmstudio.domain.banner.dto.request.CustomBannerAddMyBannerRequest;
import com.techeer.fmstudio.domain.banner.dto.request.MyBannerDeleteRequest;
import com.techeer.fmstudio.domain.banner.dto.response.BannerPageInfo;
import com.techeer.fmstudio.domain.banner.exception.NotFoundBannerException;
import com.techeer.fmstudio.domain.member.dao.MemberRepository;
import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.domain.member.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WholeBannerService {

    private final BannerRepository bannerRepository;
    private final MemberRepository memberRepository;
    private final MyBannerListRepository myBannerListRepository;

    private final BannerMapper bannerMapper;

    @Transactional(readOnly = true)
    public BannerPageInfo getWholeBannerByPagination(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<BannerEntity> pageResult = bannerRepository.findWholeBannerWithPagination(pageRequest);
        return bannerMapper.mapEntityToBannerPageInfo(pageResult, page, size);
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

    public void deleteMyBanner(MyBannerDeleteRequest request, Long bannerId){
        MemberEntity member = memberRepository.findMemberEntityByNickname(request.getNickname())
                .orElseThrow(NotFoundMemberException::new);
        myBannerListRepository.deleteByMemberIdAndBannerId(member.getId(), bannerId);
    }
}
