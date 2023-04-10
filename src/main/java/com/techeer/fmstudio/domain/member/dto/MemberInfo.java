package com.techeer.fmstudio.domain.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberInfo {

    private String login_id;

    private String login_password;

}
