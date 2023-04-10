package com.techeer.fmstudio.domain.member.dto;

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
    private String loginId;

    @NotBlank
    private String loginPassword;

    @NotBlank
    private String nickname;

    @NotNull
    private String introduction;

    private String status;

    private List<String> interestList;
}
