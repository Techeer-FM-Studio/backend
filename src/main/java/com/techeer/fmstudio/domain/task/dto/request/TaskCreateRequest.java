package com.techeer.fmstudio.domain.task.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class TaskCreateRequest {
    @NotBlank
    private String writer;
    @NotBlank
    private String title;
    @NotBlank
    private String memo;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startAt;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endAt;
    @NotNull
    private Boolean isFinished;
    @NotNull
    private Boolean isOpened;
}