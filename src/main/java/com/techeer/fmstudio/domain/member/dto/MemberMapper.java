package com.techeer.fmstudio.domain.member.dto;

import com.techeer.fmstudio.domain.member.domain.MemberEntity;
import com.techeer.fmstudio.domain.member.domain.MemberInterest;
import com.techeer.fmstudio.domain.member.domain.MemberStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberMapper {
    public MemberEntity toEntity(MemberCreateRequest request) {
        List<MemberInterest> interestList = new ArrayList<>();
        for(String interestStr : request.getInterestList()){
            MemberInterest interest = MemberInterest.valueOf(interestStr);
            interestList.add(interest);
        }
        return MemberEntity.builder()
                .loginId(request.getLoginId())
                .loginPassword(request.getLoginPassword())
                .nickname(request.getNickname())
                .introduction(request.getIntroduction())
                .interestList(interestList)
                .memberStatus(MemberStatus.valueOf(request.getStatus()))
                .build();
    }

    public MemberInfo toInfo(MemberEntity entity) {
        return MemberInfo.builder()
                .login_id(entity.getLoginId())
                .login_password(entity.getLoginPassword())
                .build();
    }
}
