package com.techeer.fmstudio.domain.task.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TestMemberCreateRequest {
    private String loginId;
    private String loginPassword;

    private String nickname;
    private String status;
    private String introduction;
    private String interests;
}
