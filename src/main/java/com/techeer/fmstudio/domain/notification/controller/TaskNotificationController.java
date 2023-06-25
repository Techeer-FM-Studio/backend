package com.techeer.fmstudio.domain.notification.controller;

import com.techeer.fmstudio.domain.notification.service.TaskNotificationService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class TaskNotificationController {
  private final TaskNotificationService taskNotificationService;

  @DeleteMapping("/tasks/notification/{id}")
  @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
  public ResponseEntity<String> deleteTaskNotification(@Valid @PathVariable Long id) {
    taskNotificationService.deleteTaskNotification(id);

    return ResponseEntity.status(HttpStatus.OK)
        .body("알림이 삭제되었습니다.");
  }
}
