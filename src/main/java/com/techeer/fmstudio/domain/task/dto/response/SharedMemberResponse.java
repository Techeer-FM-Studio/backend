package com.techeer.fmstudio.domain.task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class SharedMemberResponse {
    private Long taskId;
    private Long memberId;

}