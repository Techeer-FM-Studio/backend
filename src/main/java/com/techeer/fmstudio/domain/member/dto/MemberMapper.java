package com.techeer.fmstudio.domain.member.dto;

import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public MemberEntity toEntity(MemberCreateRequest request) {
        return MemberEntity.builder()
                .loginId(request.getLogin_id())
                .loginPassword(request.getLogin_password())
                .nickname(request.getNickname())
                .introduction(request.getIntroduction())
                .interestList(request.getInterestList())
                .memberStatus(request.getStatus())
                .build();
    }

    public MemberInfo toInfo(MemberEntity entity) {
        return MemberInfo.builder()
                .login_id(entity.getLoginId())
                .login_password(entity.getLoginPassword())
                .build();
    }
}
