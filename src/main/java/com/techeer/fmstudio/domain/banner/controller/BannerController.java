package com.techeer.fmstudio.domain.banner.controller;

import com.techeer.fmstudio.domain.banner.domain.Banner;
import com.techeer.fmstudio.domain.banner.dto.BannerCreateRequest;
import com.techeer.fmstudio.domain.banner.dto.BannerInfo;
import com.techeer.fmstudio.domain.banner.dto.BannerMapper;
import com.techeer.fmstudio.domain.banner.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/banner")
public class BannerController {

    private final BannerService bannerService;
    private final BannerMapper bannerMapper;

    @PostMapping
    public ResponseEntity<BannerInfo> create(@Valid @RequestBody BannerCreateRequest request) {
        Banner newBanner = bannerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bannerMapper.toInfo(newBanner));
    }
}
