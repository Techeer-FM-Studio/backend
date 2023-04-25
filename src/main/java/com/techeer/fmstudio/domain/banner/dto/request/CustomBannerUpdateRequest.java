package com.techeer.fmstudio.domain.banner.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomBannerUpdateRequest {

    @NotNull(message = "Member writer of banner owner is required")
    private String writer;

    @NotBlank(message = "Banner title is required")
    private String title;

    @NotBlank(message = "Banner memo is required")
    private String memo;

    @NotNull(message = "Banner start date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startedAt;

    @NotNull(message = "Banner end date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endAt;

    private List<String> imageUrl;

}
