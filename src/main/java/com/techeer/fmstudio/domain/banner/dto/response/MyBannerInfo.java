package com.techeer.fmstudio.domain.banner.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyBannerInfo {
    private String nickname;
    private Long bannerId;
}
