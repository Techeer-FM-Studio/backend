package com.techeer.fmstudio.domain.task.exception;

import com.techeer.fmstudio.global.error.exception.BusinessException;
import com.techeer.fmstudio.global.error.exception.ErrorCode;

public class NotFoundTaskException extends BusinessException {
    public NotFoundTaskException() {
        super(ErrorCode.NOT_FOUND_TASK_ENTITY);
    }
}