package com.techeer.fmstudio.domain.banner.controller;

import com.techeer.fmstudio.domain.banner.domain.BannerEntity;
import com.techeer.fmstudio.domain.banner.dto.request.CustomBannerCreateRequest;
import com.techeer.fmstudio.domain.banner.dto.request.CustomBannerUpdateRequest;
import com.techeer.fmstudio.domain.banner.dto.response.BannerInfo;
import com.techeer.fmstudio.domain.banner.dto.mapper.BannerMapper;
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

    private final CustomBannerService customBannerService;
    private final BannerMapper bannerMapper;

    @PostMapping
    public ResponseEntity<BannerInfo> create(@Valid @RequestBody CustomBannerCreateRequest request) {
        BannerEntity newBanner = customBannerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bannerMapper.toBannerInfo(newBanner));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BannerInfo> getOne(@PathVariable Long id){
        BannerEntity foundBanner = customBannerService.getOne(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(bannerMapper.toBannerInfo(foundBanner));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(
            @Valid @RequestBody CustomBannerUpdateRequest request,
            @PathVariable Long id
            ){
        String result = customBannerService.update(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        BannerEntity deletedBanner = customBannerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("배너 id " + deletedBanner.getId().toString() + "가 삭제 되었습니다.");
    }
}
