package com.techeer.fmstudio.domain.banner.controller;

import com.techeer.fmstudio.domain.banner.dto.mapper.BannerMapper;
import com.techeer.fmstudio.domain.banner.dto.response.BannerPageInfo;
import com.techeer.fmstudio.domain.banner.service.WholeBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
}
