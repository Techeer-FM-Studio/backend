package com.techeer.fmstudio.domain.task.dto.response;

import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.domain.TestMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class SharedMemberInfo {
    private Long testMemberId;
    private Long taskId;
}