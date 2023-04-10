package com.techeer.fmstudio.domain.member.dto;

import com.techeer.fmstudio.domain.member.domain.MemberInterest;
import com.techeer.fmstudio.domain.member.domain.MemberStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberCreateRequest {

    @NotBlank
    private String login_id;

    @NotBlank
    private String login_password;

    @NotBlank
    private String nickname;

    @NotNull
    private String introduction;

    private MemberStatus status;

    private List<MemberInterest> interestList;
}
