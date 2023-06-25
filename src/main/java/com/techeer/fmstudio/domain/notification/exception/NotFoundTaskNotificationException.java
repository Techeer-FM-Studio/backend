package com.techeer.fmstudio.domain.notification.exception;

import com.techeer.fmstudio.global.error.exception.BusinessException;
import com.techeer.fmstudio.global.error.exception.ErrorCode;

public class NotFoundTaskNotificationException extends BusinessException {
  public NotFoundTaskNotificationException() {
    super(ErrorCode.NOT_FOUND_TASK_NOTIFICATION_ENTITY);
  }
}
