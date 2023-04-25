package com.techeer.fmstudio.domain.banner.controller;

import com.techeer.fmstudio.domain.banner.domain.BannerEntity;
import com.techeer.fmstudio.domain.banner.dto.CustomBannerCreateRequest;
import com.techeer.fmstudio.domain.banner.dto.BannerInfo;
import com.techeer.fmstudio.domain.banner.dto.BannerMapper;
import com.techeer.fmstudio.domain.banner.service.CustomBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/banners/custom")
public class CustomBannerController {

    private final CustomBannerService bannerService;
    private final BannerMapper bannerMapper;

    @PostMapping
    public ResponseEntity<BannerInfo> create(@Valid @RequestBody CustomBannerCreateRequest request) {
        BannerEntity newBanner = bannerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bannerMapper.toInfo(newBanner));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> create(@PathVariable Long id) {
        BannerEntity deletedBanner = bannerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("배너 id " + deletedBanner.getId().toString() + "가 삭제 되었습니다.");
    }
}
