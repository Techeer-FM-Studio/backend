package com.techeer.fmstudio.domain.notification.dto.mapper;

import com.techeer.fmstudio.domain.notification.dao.TaskNotificationRepository;
import com.techeer.fmstudio.domain.notification.domain.TaskNotification;
import com.techeer.fmstudio.domain.notification.dto.request.TaskNotificationCreateRequest;
import com.techeer.fmstudio.domain.task.dao.SharedMemberRepository;
import com.techeer.fmstudio.domain.task.domain.SharedMember;
import com.techeer.fmstudio.domain.task.exception.NotFoundSharedMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskNotificationMapper {

  public TaskNotification mapTaskNotificationCreateRequestToTaskNotificationEntity(
      TaskNotificationCreateRequest taskNotificationCreateRequest) {
    return TaskNotification.builder()
        .sharedMember(taskNotificationCreateRequest.getSharedMember())
        .receiverNickname(taskNotificationCreateRequest.getReceiverNickname())
        .senderNickname(taskNotificationCreateRequest.getSenderNickname())
        .readStatus(false)
        .build();
  }
}
