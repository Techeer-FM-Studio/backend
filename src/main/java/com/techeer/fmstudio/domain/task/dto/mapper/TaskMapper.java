package com.techeer.fmstudio.domain.task.dto.mapper;

import com.techeer.fmstudio.domain.task.dao.SharedMemberRepository;
import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.domain.TestMember;
import com.techeer.fmstudio.domain.task.dto.request.TaskCreateRequest;
import com.techeer.fmstudio.domain.task.dto.response.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class TaskMapper {
    private final SharedMemberRepository sharedMemberRepository;
    private final TestMemberMapper testMemberMapper;

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

    public TaskResponse mapTaskEntityToTaskInfo(Task task) {
        return TaskResponse.builder()
                .taskId(task.getId())
                .writer(task.getWriter())
                .title(task.getTitle())
                .memo(task.getMemo())
                .startAt(task.getStartAt())
                .endAt(task.getEndAt())
                .isFinished(task.getIsFinished())
                .sharedMemberInfoList(task.getSharedMemberList())
                .build();
    }

    public TaskResponse mapTaskEntityToTaskInfo(Task task, List<String> foundTestMemberList) {
        return TaskResponse.builder()
                .taskId(task.getId())
                .writer(task.getWriter())
                .title(task.getTitle())
                .memo(task.getMemo())
                .startAt(task.getStartAt())
                .endAt(task.getEndAt())
                .isFinished(task.getIsFinished())
                .sharedMemberInfoList(foundTestMemberList)
                .build();
    }


}
