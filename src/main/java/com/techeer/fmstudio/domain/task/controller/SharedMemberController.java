package com.techeer.fmstudio.domain.task.controller;

import com.techeer.fmstudio.domain.task.dto.mapper.SharedMemberMapper;
import com.techeer.fmstudio.domain.task.dto.response.SharedMemberResponse;
import com.techeer.fmstudio.domain.task.service.SharedMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class SharedMemberController {
    private final SharedMemberService sharedMemberService;
    private final SharedMemberMapper sharedMemberMapper;

    @GetMapping("/shared-members/list")
    public ResponseEntity<List<SharedMemberResponse>> getSharedMemberList(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<SharedMemberResponse> sharedMemberResponseList = sharedMemberService.getSharedMemberList(page, size);
        return ResponseEntity.ok(sharedMemberResponseList);
    }
}
