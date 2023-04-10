package com.techeer.fmstudio.domain.task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class TaskResponse {
    private Long taskId;
    private String writer;
    private String title;
    private String memo;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Boolean isFinished;
    private List<String> sharedMemberInfoList;
}
