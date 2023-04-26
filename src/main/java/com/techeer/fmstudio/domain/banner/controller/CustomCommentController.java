package com.techeer.fmstudio.domain.banner.controller;

import com.techeer.fmstudio.domain.banner.domain.CommentEntity;
import com.techeer.fmstudio.domain.banner.dto.mapper.CommentMapper;
import com.techeer.fmstudio.domain.banner.dto.request.CommentCreateRequest;
import com.techeer.fmstudio.domain.banner.dto.response.CommentInfo;
import com.techeer.fmstudio.domain.banner.dto.response.CommentPageInfo;
import com.techeer.fmstudio.domain.banner.service.CustomCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/banners/custom/{bannerId}/comments")
public class CustomCommentController {

    private final CustomCommentService commentService;
    private final CommentMapper commentMapper;
    @PostMapping
    public ResponseEntity<CommentInfo> create(
            @Valid @RequestBody CommentCreateRequest request,
            @PathVariable Long bannerId
    ){
        CommentEntity newComment = commentService.create(request, bannerId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentMapper.toCommentInfo(newComment));
    }

    @GetMapping("/page")
    public ResponseEntity<CommentPageInfo> getCustomBannerCommentByPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @PathVariable Long bannerId
    ){
        CommentPageInfo foundCommentList = commentService.getCustomBannerCommentByPagination(bannerId, page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(foundCommentList);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> delete(@PathVariable Long commentId){
        commentService.delete(commentId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("삭제 되었습니다.");
    }
}
