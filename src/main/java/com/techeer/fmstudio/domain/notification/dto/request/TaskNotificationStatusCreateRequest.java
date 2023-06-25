package com.techeer.fmstudio.domain.notification.dto.request;

import com.techeer.fmstudio.domain.notification.domain.TaskNotification;
import com.techeer.fmstudio.domain.task.domain.SharedMember;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TaskNotificationStatusCreateRequest {
  @NotBlank
  private TaskNotification taskNotification;
}
