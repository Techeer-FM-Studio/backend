package com.techeer.fmstudio.domain.task.dto.mapper;

import com.techeer.fmstudio.domain.task.domain.TestMember;
import com.techeer.fmstudio.domain.task.dto.request.TestMemberCreateRequest;
import com.techeer.fmstudio.domain.task.dto.response.TestMemberResponse;
import org.springframework.stereotype.Component;

@Component
public class TestMemberMapper {

    public TestMemberResponse mapTestMemberEntityToTestMemberInfo(TestMember testMember) {
        return TestMemberResponse.builder()
                .nickname(testMember.getNickname())
                .status(testMember.getStatus())
                .introduction(testMember.getIntroduction())
                .interests(testMember.getInterests())
                .build();
    }

    public TestMemberResponse mapTestMemberCreateRequestToTestMemberInfo(TestMemberCreateRequest testMemberCreateRequest) {
        return TestMemberResponse.builder()
                .nickname(testMemberCreateRequest.getNickname())
                .interests(testMemberCreateRequest.getInterests())
                .introduction(testMemberCreateRequest.getIntroduction())
                .status(testMemberCreateRequest.getStatus())
                .build();
    }

    public TestMember mapTestMemberCreateRequestToTestMember(TestMemberCreateRequest testMemberCreateRequest) {
        return TestMember.builder()
                .nickname(testMemberCreateRequest.getNickname())
                .loginId(testMemberCreateRequest.getLoginId())
                .loginPassword(testMemberCreateRequest.getLoginPassword())
                .interests(testMemberCreateRequest.getInterests())
                .introduction(testMemberCreateRequest.getIntroduction())
                .status(testMemberCreateRequest.getStatus())
                .build();
    }
}
