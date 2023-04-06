package com.techeer.fmstudio.domain.task.dto.mapper;

import com.techeer.fmstudio.domain.task.domain.TestMember;
import com.techeer.fmstudio.domain.task.dto.request.TestMemberCreateRequest;
import com.techeer.fmstudio.domain.task.dto.response.TestMemberInfo;
import org.springframework.stereotype.Component;

@Component
public class TestMemberMapper {

    public TestMemberInfo mapTestMemberEntityToTestMemberInfo(TestMember testMember) {
        return TestMemberInfo.builder()
                .nickname(testMember.getNickname())
                .status(testMember.getStatus())
                .introduction(testMember.getIntroduction())
                .interests(testMember.getInterests())
                .build();
    }

    public TestMemberInfo mapTestMemberCreateRequestToTestMemberInfo(TestMemberCreateRequest testMemberCreateRequest) {
        return TestMemberInfo.builder()
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
