package com.techeer.fmstudio.domain.task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class TaskInfo {
    private String writer;
    private String title;
    private String memo;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Boolean isFinished;
    private Boolean isOpened;
}
