package com.techeer.fmstudio.domain.task.dto.mapper;

import com.techeer.fmstudio.domain.task.dao.SharedMemberRepository;
import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.dto.request.TaskCreateRequest;
import com.techeer.fmstudio.domain.task.dto.response.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
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
                .build();
    }

    public TaskResponse mapTaskEntityToTaskResponse(Task task) {
        return TaskResponse.builder()
                .taskId(task.getId())
                .writer(task.getWriter())
                .title(task.getTitle())
                .memo(task.getMemo())
                .startAt(task.getStartAt())
                .endAt(task.getEndAt())
                .isFinished(task.getIsFinished())
                .sharedMemberNicknameList(task.getSharedMemberList())
                .build();
    }

    public TaskResponse mapTaskEntityToTaskResponse(Task task, List<String> foundMemberEntityList) {
        return TaskResponse.builder()
                .taskId(task.getId())
                .writer(task.getWriter())
                .title(task.getTitle())
                .memo(task.getMemo())
                .startAt(task.getStartAt())
                .endAt(task.getEndAt())
                .isFinished(task.getIsFinished())
                .sharedMemberNicknameList(foundMemberEntityList)
                .build();
    }


}
