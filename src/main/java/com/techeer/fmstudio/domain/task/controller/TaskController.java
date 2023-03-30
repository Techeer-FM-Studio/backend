package com.techeer.fmstudio.domain.task.controller;

import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.domain.TestMember;
import com.techeer.fmstudio.domain.task.dto.mapper.TaskMapper;
import com.techeer.fmstudio.domain.task.dto.request.TaskCreateRequest;
import com.techeer.fmstudio.domain.task.dto.request.TestMemberCreateRequest;
import com.techeer.fmstudio.domain.task.dto.response.TaskInfo;
import com.techeer.fmstudio.domain.task.dto.response.TestMemberInfo;
import com.techeer.fmstudio.domain.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @PostMapping("/tasks")
    public ResponseEntity<TaskInfo> createTask(@RequestBody TaskCreateRequest taskCreateRequest) {
        Task task = taskService.createTask(taskCreateRequest);
        return ResponseEntity.ok(taskMapper.mapTaskEntityToTaskInfo(task));
    }

}
