package com.techeer.fmstudio.domain.task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class TestMemberResponse {
    private String nickname;
    private String status;
    private String introduction;
    private String interests;
}
