package com.techeer.fmstudio.domain.banner.service;


import com.techeer.fmstudio.domain.banner.dao.BannerRepository;
import com.techeer.fmstudio.domain.banner.domain.BannerEntity;
import com.techeer.fmstudio.domain.banner.dto.mapper.BannerMapper;
import com.techeer.fmstudio.domain.banner.dto.response.BannerPageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WholeBannerService {

    private final BannerRepository bannerRepository;

    private final BannerMapper bannerMapper;

    @Transactional(readOnly = true)
    public BannerPageInfo getWholeBannerByPagination(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<BannerEntity> pageResult = bannerRepository.findWholeBannerWithPagination(pageRequest);
        return bannerMapper.mapEntityToBannerPageInfo(pageResult, page, size);
    }

}
