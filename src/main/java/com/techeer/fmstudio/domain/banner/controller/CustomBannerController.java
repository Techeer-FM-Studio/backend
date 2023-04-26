package com.techeer.fmstudio.domain.banner.controller;

import com.techeer.fmstudio.domain.banner.domain.BannerEntity;
import com.techeer.fmstudio.domain.banner.dto.request.CustomBannerCreateRequest;
import com.techeer.fmstudio.domain.banner.dto.request.CustomBannerUpdateRequest;
import com.techeer.fmstudio.domain.banner.dto.response.BannerInfo;
import com.techeer.fmstudio.domain.banner.dto.mapper.BannerMapper;
import com.techeer.fmstudio.domain.banner.dto.response.BannerPageInfo;
import com.techeer.fmstudio.domain.banner.dto.response.DeleteResponse;
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

    @GetMapping("/page")
    public ResponseEntity<BannerPageInfo> getCustomBannerByPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
    ){
        BannerPageInfo foundBannerList = customBannerService.getCustomBannerByPagination(page,size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(foundBannerList);
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
    public ResponseEntity<DeleteResponse> delete(@PathVariable Long id) {
        BannerEntity deletedBanner = customBannerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(DeleteResponse.builder().id(id).build());
    }
}
