package com.techeer.fmstudio.domain.task.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
public class TestMemberCreateRequest {
    @NotBlank
    private String loginId;
    @NotBlank
    private String loginPassword;
    @NotBlank
    private String nickname;
    private String status;
    private String introduction;
    private String interests;
}
