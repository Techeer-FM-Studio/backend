package com.techeer.fmstudio.domain.task.controller;

import com.techeer.fmstudio.domain.task.domain.TestMember;
import com.techeer.fmstudio.domain.task.dto.mapper.TestMemberMapper;
import com.techeer.fmstudio.domain.task.dto.request.TestMemberCreateRequest;
import com.techeer.fmstudio.domain.task.dto.response.TestMemberInfo;
import com.techeer.fmstudio.domain.task.service.TestMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestMemberController {
    private final TestMemberService testMemberService;
    private final TestMemberMapper testMemberMapper;
    @PostMapping("/testmembers")
    public ResponseEntity<TestMemberInfo> createTestMember(@RequestBody TestMemberCreateRequest testMemberCreateRequest) {
        TestMember testMember = testMemberService.createTestMember(testMemberCreateRequest);
        return ResponseEntity.ok(testMemberMapper.mapTestMemberEntityToTestMemberInfo(testMember));
    }
}
