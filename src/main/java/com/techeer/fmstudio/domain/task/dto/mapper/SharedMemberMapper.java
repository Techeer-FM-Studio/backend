package com.techeer.fmstudio.domain.task.dto.mapper;

import com.techeer.fmstudio.domain.task.domain.SharedMember;
import com.techeer.fmstudio.domain.task.dto.response.SharedMemberInfo;
import org.springframework.stereotype.Component;

@Component
public class SharedMemberMapper {
    public SharedMemberInfo mapSharedMemberEntityToSharedMemberInfo(SharedMember sharedMember) {
        return SharedMemberInfo.builder()
                .testMemberId(sharedMember.getTestMember().getId())
                .taskId(sharedMember.getTask().getId())
                .build();
    }
}
