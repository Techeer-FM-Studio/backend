package com.techeer.fmstudio.domain.banner.controller;

import com.techeer.fmstudio.domain.banner.domain.CommentEntity;
import com.techeer.fmstudio.domain.banner.dto.mapper.CommentMapper;
import com.techeer.fmstudio.domain.banner.dto.request.CommentCreateRequest;
import com.techeer.fmstudio.domain.banner.dto.response.CommentInfo;
import com.techeer.fmstudio.domain.banner.service.CustomCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/banners/custom")
public class CustomCommentController {

    private final CustomCommentService commentService;
    private final CommentMapper commentMapper;
    @PostMapping("/{bannerId}/comments")
    public ResponseEntity<CommentInfo> create(
            @Valid @RequestBody CommentCreateRequest request,
            @PathVariable Long bannerId
    ){
        CommentEntity newComment = commentService.create(request, bannerId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentMapper.toInfo(newComment));
    }

    @DeleteMapping("/{bannerId}/comments/{commentId}")
    public ResponseEntity<String> delete(@PathVariable Long commentId){
        commentService.delete(commentId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("삭제 되었습니다.");
    }
}
