package com.techeer.fmstudio.domain.banner.controller;

import com.techeer.fmstudio.domain.banner.domain.MyBannerList;
import com.techeer.fmstudio.domain.banner.dto.mapper.BannerMapper;
import com.techeer.fmstudio.domain.banner.dto.request.CustomBannerAddMyBannerRequest;
import com.techeer.fmstudio.domain.banner.dto.response.BannerPageInfo;
import com.techeer.fmstudio.domain.banner.dto.response.MyBannerInfo;
import com.techeer.fmstudio.domain.banner.service.WholeBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/banners")
public class WholeBannerController {

    private final WholeBannerService wholeBannerService;
    private final BannerMapper bannerMapper;

    @GetMapping("/page")
    public ResponseEntity<BannerPageInfo> getWholeBannerByPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
    ){
        BannerPageInfo foundBannerList = wholeBannerService.getWholeBannerByPagination(page,size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(foundBannerList);
    }

    @PostMapping("mybanners/{id}")
    public ResponseEntity<MyBannerInfo> addBannerToMyList(
            @Valid @RequestBody CustomBannerAddMyBannerRequest request,
            @PathVariable Long id
    ){

        MyBannerList myBannerList = wholeBannerService.addMyBanner(request, id);
        MyBannerInfo myBannerInfo = MyBannerInfo.builder()
                .nickname(myBannerList.getMember().getNickname())
                .bannerId(myBannerList.getBanner().getId())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(myBannerInfo);
    }
}
