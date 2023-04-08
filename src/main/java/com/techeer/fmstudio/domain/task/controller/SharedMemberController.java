package com.techeer.fmstudio.domain.task.controller;

import com.techeer.fmstudio.domain.task.dao.SharedMemberRepository;
import com.techeer.fmstudio.domain.task.dto.mapper.SharedMemberMapper;
import com.techeer.fmstudio.domain.task.dto.response.SharedMemberInfo;
import com.techeer.fmstudio.domain.task.dto.response.TaskInfo;
import com.techeer.fmstudio.domain.task.service.SharedMemberService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<SharedMemberInfo>> getSharedMemberList(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<SharedMemberInfo> sharedMemberInfoList = sharedMemberService.getSharedMemberList(page, size);
        return ResponseEntity.ok(sharedMemberInfoList);
    }
}
