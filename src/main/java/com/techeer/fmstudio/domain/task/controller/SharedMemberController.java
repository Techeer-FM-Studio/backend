package com.techeer.fmstudio.domain.task.controller;

import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.dto.mapper.SharedMemberMapper;
import com.techeer.fmstudio.domain.task.dto.request.SharedMemberCreateRequest;
import com.techeer.fmstudio.domain.task.dto.response.SharedMemberResponse;
import com.techeer.fmstudio.domain.task.service.SharedMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sharedMemberResponseList);
    }

    @PostMapping("/shared-members")
    public ResponseEntity<SharedMemberResponse> createSharedMember(@Valid @RequestBody SharedMemberCreateRequest sharedMemberCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sharedMemberService.createSharedMemberById(sharedMemberCreateRequest));
    }

    @DeleteMapping("/shared-members/{id}")
    public ResponseEntity<String> deleteSharedMember(@Valid @PathVariable Long id) {
        sharedMemberService.deleteSharedMember(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("일정에서 공유된 사용자가 삭제되었습니다.");
    }
}
