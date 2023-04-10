package com.techeer.fmstudio.domain.member.controller;

import com.techeer.fmstudio.domain.member.dto.MemberCreateRequest;
import com.techeer.fmstudio.domain.member.dto.MemberInfo;
import com.techeer.fmstudio.domain.member.dto.MemberMapper;
import com.techeer.fmstudio.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;
    private final MemberMapper mapper;

    @PostMapping
    public ResponseEntity<MemberInfo> register(@Valid @RequestBody MemberCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toInfo(service.register(request)));
    }
}
