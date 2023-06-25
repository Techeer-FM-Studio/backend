package com.techeer.fmstudio.domain.notification.service;

import com.techeer.fmstudio.domain.notification.dao.TaskNotificationStatusRepository;
import com.techeer.fmstudio.domain.notification.domain.TaskNotificationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class TaskNotificationStatusService {
  private final TaskNotificationStatusRepository taskNotificationStatusRepository;

  public void deleteTaskNotificationStatus(long taskNotificationStatusId) {
    TaskNotificationStatus taskNotificationStatus = taskNotificationStatusRepository.findById(
            taskNotificationStatusId).orElseThrow();
    taskNotificationStatus.deleteTaskNotificationStatus();
  }

}
