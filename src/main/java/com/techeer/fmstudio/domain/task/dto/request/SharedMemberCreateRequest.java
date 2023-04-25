package com.techeer.fmstudio.domain.task.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class SharedMemberCreateRequest {
    @NotNull
    private Long taskId;

    @NotBlank
    private String memberNickname;

}
