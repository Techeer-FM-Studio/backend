package com.techeer.fmstudio.domain.banner.dto.request;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomBannerAddMyBannerRequest {

    @NotBlank(message = "Member nickname is required.")
    private String nickname;

}
