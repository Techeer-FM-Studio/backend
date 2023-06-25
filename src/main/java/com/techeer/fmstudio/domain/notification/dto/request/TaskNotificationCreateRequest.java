package com.techeer.fmstudio.domain.notification.dto.request;

import com.techeer.fmstudio.domain.task.domain.SharedMember;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TaskNotificationCreateRequest {
  @NotBlank
  private SharedMember sharedMember;
  @NotBlank
  private String receiverNickname;
  @NotBlank
  private String senderNickname;
}
