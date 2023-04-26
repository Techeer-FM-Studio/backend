package com.techeer.fmstudio.domain.banner.controller;

import com.techeer.fmstudio.domain.banner.domain.MyBannerList;
import com.techeer.fmstudio.domain.banner.dto.request.CustomBannerAddMyBannerRequest;
import com.techeer.fmstudio.domain.banner.dto.request.MyBannerDeleteRequest;
import com.techeer.fmstudio.domain.banner.dto.response.DeleteResponse;
import com.techeer.fmstudio.domain.banner.dto.response.MyBannerCreateInfo;
import com.techeer.fmstudio.domain.banner.dto.response.MyBannerInfo;
import com.techeer.fmstudio.domain.banner.service.MyBannerListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/banners/mybanners")
public class MyBannerListController {

    private final MyBannerListService myBannerListService;

    @PostMapping("/{id}")
    public ResponseEntity<MyBannerCreateInfo> addBannerToMyList(
            @Valid @RequestBody CustomBannerAddMyBannerRequest request,
            @PathVariable Long id
    ){

        MyBannerList myBannerList = myBannerListService.addMyBanner(request, id);
        MyBannerCreateInfo myBannerInfo = MyBannerCreateInfo.builder()
                .nickname(myBannerList.getMember().getNickname())
                .bannerId(myBannerList.getBanner().getId())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(myBannerInfo);
    }

    @GetMapping("/list")
    public ResponseEntity<List<MyBannerInfo>> getMyBannerWithPagination(
            @RequestParam String memberId,
            @RequestParam int year,
            @RequestParam int month
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(myBannerListService.getMyBannerWithPagination(memberId, year, month));
    }

    @DeleteMapping("/{bannerId}")
    public ResponseEntity<DeleteResponse> deleteMyBanner(
            @Valid @RequestBody MyBannerDeleteRequest request,
            @PathVariable Long bannerId
    ){

        myBannerListService.deleteMyBanner(request, bannerId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(DeleteResponse.builder().id(bannerId).build());
    }
}
