package com.techeer.fmstudio.domain.task.dto.mapper;

import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.dto.request.TaskCreateRequest;
import com.techeer.fmstudio.domain.task.dto.response.TaskInfo;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task mapTaskCreateRequestToTaskEntity(TaskCreateRequest taskCreateRequest) {
        return Task.builder()
                .writer(taskCreateRequest.getWriter())
                .title(taskCreateRequest.getTitle())
                .memo(taskCreateRequest.getMemo())
                .startAt(taskCreateRequest.getStartAt())
                .endAt(taskCreateRequest.getEndAt())
                .isFinished(taskCreateRequest.getIsFinished())
                .isOpened(taskCreateRequest.getIsOpened())
                .build();
    }

    public TaskInfo mapTaskEntityToTaskInfo(Task task) {
        return TaskInfo.builder()
                .writer(task.getWriter())
                .title(task.getTitle())
                .memo(task.getMemo())
                .startAt(task.getStartAt())
                .endAt(task.getEndAt())
                .isFinished(task.getIsFinished())
                .isOpened(task.getIsOpened())
                .build();
    }
}
