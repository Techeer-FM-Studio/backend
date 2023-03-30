package com.techeer.fmstudio.domain.task.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class TestMemberInfo {
    private String nickname;
    private String status;
    private String introduction;
    private String interests;
}
