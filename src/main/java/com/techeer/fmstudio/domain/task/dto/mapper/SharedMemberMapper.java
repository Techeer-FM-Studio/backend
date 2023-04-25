package com.techeer.fmstudio.domain.task.dto.mapper;

import com.techeer.fmstudio.domain.member.dao.MemberRepository;
import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.domain.member.exception.NotFoundMemberException;
import com.techeer.fmstudio.domain.task.domain.SharedMember;
import com.techeer.fmstudio.domain.task.dto.response.SharedMemberResponse;
import org.springframework.stereotype.Component;

@Component
public class SharedMemberMapper {

    public SharedMemberResponse mapSharedMemberEntityToSharedMemberResponse(SharedMember sharedMember) {
        return SharedMemberResponse.builder()
                .memberId(sharedMember.getMemberEntity().getId())
                .taskId(sharedMember.getTask().getId())
                .build();
    }

    public Long mapSharedMemberEntityToTaskEntity(SharedMember sharedMember) {
        return sharedMember.getTask().getId();
    }
}
