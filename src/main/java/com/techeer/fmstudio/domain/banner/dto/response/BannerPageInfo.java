package com.techeer.fmstudio.domain.banner.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BannerPageInfo {
    private int totalPages;

    private long totalElements;
    private int size;
    private int page;

    private List<BannerInfo> content;
}
