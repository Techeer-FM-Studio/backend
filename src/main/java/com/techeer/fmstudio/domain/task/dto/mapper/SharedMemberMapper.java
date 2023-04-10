package com.techeer.fmstudio.domain.task.dto.mapper;

import com.techeer.fmstudio.domain.task.domain.SharedMember;
import com.techeer.fmstudio.domain.task.dto.response.SharedMemberResponse;
import org.springframework.stereotype.Component;

@Component
public class SharedMemberMapper {
    public SharedMemberResponse mapSharedMemberEntityToSharedMemberInfo(SharedMember sharedMember) {
        return SharedMemberResponse.builder()
                .testMemberId(sharedMember.getTestMember().getId())
                .taskId(sharedMember.getTask().getId())
                .build();
    }
}