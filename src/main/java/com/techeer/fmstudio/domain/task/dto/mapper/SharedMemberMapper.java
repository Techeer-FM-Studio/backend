package com.techeer.fmstudio.domain.task.dto.mapper;

import com.techeer.fmstudio.domain.task.domain.SharedMember;
import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.dto.response.SharedMemberResponse;
import org.springframework.stereotype.Component;

@Component
public class SharedMemberMapper {
    public SharedMemberResponse mapSharedMemberEntityToSharedMemberInfo(SharedMember sharedMember) {
        return SharedMemberResponse.builder()
                .testMemberId(sharedMember.getMemberEntity().getId())
                .taskId(sharedMember.getTask().getId())
                .build();
    }

    public Long mapSharedMemberEntityToTaskEntity(SharedMember sharedMember) {
        return sharedMember.getTask().getId();
    }
}
