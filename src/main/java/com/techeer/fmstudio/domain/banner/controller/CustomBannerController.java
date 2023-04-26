package com.techeer.fmstudio.domain.banner.controller;

import com.techeer.fmstudio.domain.banner.domain.BannerEntity;
import com.techeer.fmstudio.domain.banner.domain.CommentEntity;
import com.techeer.fmstudio.domain.banner.domain.MyBannerList;
import com.techeer.fmstudio.domain.banner.dto.mapper.CommentMapper;
import com.techeer.fmstudio.domain.banner.dto.request.CommentCreateRequest;
import com.techeer.fmstudio.domain.banner.dto.request.CustomBannerAddMyBannerRequest;
import com.techeer.fmstudio.domain.banner.dto.request.CustomBannerCreateRequest;
import com.techeer.fmstudio.domain.banner.dto.response.BannerInfo;
import com.techeer.fmstudio.domain.banner.dto.mapper.BannerMapper;
import com.techeer.fmstudio.domain.banner.dto.response.CommentInfo;
import com.techeer.fmstudio.domain.banner.dto.response.MyBannerInfo;
import com.techeer.fmstudio.domain.banner.service.CommentService;
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
    private final CommentService commentService;
    private final CommentMapper commentMapper;

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

    @PostMapping("mybanners/{id}")
    public ResponseEntity<MyBannerInfo> addMyBannerList(
            @Valid @RequestBody CustomBannerAddMyBannerRequest request,
            @PathVariable Long id
    ){

        MyBannerList myBannerList = bannerService.addMyBanner(request, id);
        MyBannerInfo myBannerInfo = MyBannerInfo.builder()
                .nickname(myBannerList.getMember().getNickname())
                .bannerId(myBannerList.getBanner().getId())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(myBannerInfo);
    }

    @PostMapping("/{bannerId}/comments")
    public ResponseEntity<CommentInfo> addComment(
            @Valid @RequestBody CommentCreateRequest request,
            @PathVariable Long bannerId
            ){
        CommentEntity newComment = commentService.create(request, bannerId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentMapper.toInfo(newComment));
    }
}
