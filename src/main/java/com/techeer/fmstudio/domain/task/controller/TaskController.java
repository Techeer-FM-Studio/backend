package com.techeer.fmstudio.domain.task.controller;

import com.techeer.fmstudio.domain.task.dto.mapper.TaskMapper;
import com.techeer.fmstudio.domain.task.dto.request.TaskCreateRequest;
import com.techeer.fmstudio.domain.task.dto.request.TaskUpdateRequest;
import com.techeer.fmstudio.domain.task.dto.response.TaskResponse;
import com.techeer.fmstudio.domain.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @PostMapping("/tasks")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskCreateRequest taskCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(taskCreateRequest));
    }

    @GetMapping("/tasks/create/many")
    public void createManyTask() {
        taskService.createManyTask();
    }

    @GetMapping("/tasks/{taskId}")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    public ResponseEntity<TaskResponse> getTask(@Valid @PathVariable Long taskId) {
        TaskResponse taskResponse = taskService.getTask(taskId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskResponse);
    }

    @PutMapping("/tasks")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    public ResponseEntity<TaskResponse> updateTask(@Valid @RequestBody TaskUpdateRequest taskUpdateRequest) {
        TaskResponse taskResponse = taskService.updateTask(taskUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskResponse);
    }

    @DeleteMapping("/tasks/{id}")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    public ResponseEntity<String> deleteTask(@Valid @PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("리뷰가 삭제되었습니다.");
    }

    @GetMapping("/tasks/list")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    public ResponseEntity<List<TaskResponse>> getPrivateTaskAndSharedTask(@Valid @RequestParam String memberId,
                                                                       @Valid @RequestParam Integer year,
                                                                       @Valid @RequestParam Integer month) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.getPrivateTaskAndSharedTask(memberId, year, month));
    }

    @GetMapping("/previous-month-tasks/list")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    public ResponseEntity<List<TaskResponse>> getPreviousMonthTask(@Valid @RequestParam String memberId,
                                                                          @Valid @RequestParam Integer year,
                                                                          @Valid @RequestParam Integer month) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.getPreviousMonthTask(memberId, year, month));
    }

    @GetMapping("/next-month-tasks/list")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    public ResponseEntity<List<TaskResponse>> getNextMonthTask(@Valid @RequestParam String memberId,
                                                               @Valid @RequestParam Integer year,
                                                               @Valid @RequestParam Integer month) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.getNextMonthTask(memberId, year, month));
    }

    @GetMapping("/tasks/privacy/list")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    public ResponseEntity<List<TaskResponse>> getWriterTaskByYearAndMonth(@Valid @RequestParam String memberId,
                                                                    @Valid @RequestParam Integer year,
                                                                    @Valid @RequestParam Integer month) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.getWriterTaskByYearAndMonth(memberId, year, month));
    }

    @GetMapping("/tasks/sharing/list")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    public ResponseEntity<List<TaskResponse>> getSharedTaskByYearAndMonth(@Valid @RequestParam String memberId,
                                                                          @Valid @RequestParam Integer year,
                                                                          @Valid @RequestParam Integer month) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.getSharedTaskByYearAndMonth(memberId, year, month));
    }
}
