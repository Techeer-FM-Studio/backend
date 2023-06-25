package com.techeer.fmstudio.domain.notification.service;

import com.techeer.fmstudio.domain.notification.dao.TaskNotificationRepository;
import com.techeer.fmstudio.domain.notification.dao.TaskNotificationStatusRepository;
import com.techeer.fmstudio.domain.notification.domain.TaskNotification;
import com.techeer.fmstudio.domain.notification.domain.TaskNotificationStatus;
import com.techeer.fmstudio.domain.notification.dto.mapper.TaskNotificationMapper;
import com.techeer.fmstudio.domain.notification.dto.request.TaskNotificationCreateRequest;
import com.techeer.fmstudio.domain.notification.exception.NotFoundTaskNotificationException;
import com.techeer.fmstudio.domain.task.dto.request.TaskCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class TaskNotificationService {
    private final TaskNotificationRepository taskNotificationRepository;
    private final TaskNotificationStatusRepository taskNotificationStatusRepository;
    private final TaskNotificationMapper taskNotificationMapper;

    @Transactional
    public void createTaskNotification(TaskNotificationCreateRequest taskNotificationCreateRequest) {
        TaskNotification taskNotification = taskNotificationMapper.mapTaskNotificationCreateRequestToTaskNotificationEntity(
            taskNotificationCreateRequest);
        TaskNotification savedTaskNotification = taskNotificationRepository.save(taskNotification);

        TaskNotificationStatus taskNotificationStatus = TaskNotificationStatus.builder()
            .taskNotification(savedTaskNotification)
            .acceptStatus(false)
            .readStatus(false)
            .build();

        taskNotificationStatusRepository.save(taskNotificationStatus);
    }

    @Transactional
    public void deleteTaskNotification(Long taskNotificationId) {
        TaskNotification taskNotification = taskNotificationRepository.findById(taskNotificationId)
            .orElseThrow(
                NotFoundTaskNotificationException::new);
        taskNotification.deleteTaskNotification();
    }
}
