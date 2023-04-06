package com.techeer.fmstudio.domain.task.controller;

import com.techeer.fmstudio.domain.task.domain.Task;
import com.techeer.fmstudio.domain.task.dto.mapper.TaskMapper;
import com.techeer.fmstudio.domain.task.dto.request.TaskCreateRequest;
import com.techeer.fmstudio.domain.task.dto.request.TaskUpdateRequest;
import com.techeer.fmstudio.domain.task.dto.response.TaskInfo;
import com.techeer.fmstudio.domain.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @PostMapping("/tasks")
    public ResponseEntity<TaskInfo> createTask(@Valid @RequestBody TaskCreateRequest taskCreateRequest) {
        Task task = taskService.createTask(taskCreateRequest);
        return ResponseEntity.ok(taskMapper.mapTaskEntityToTaskInfo(task));
    }

    @PutMapping("/tasks")
    public ResponseEntity<TaskInfo> updateTask(@Valid @RequestBody TaskUpdateRequest taskUpdateRequest) {
        TaskInfo taskInfo = taskService.updateTask(taskUpdateRequest);
        return ResponseEntity.ok(taskInfo);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> deleteTask(@Valid @PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("리뷰가 삭제되었습니다.");
    }
}
