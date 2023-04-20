package com.techeer.fmstudio.domain.task.exception;

import com.techeer.fmstudio.global.error.exception.BusinessException;
import com.techeer.fmstudio.global.error.exception.ErrorCode;

public class NotFoundSharedMemberException extends BusinessException {
    public NotFoundSharedMemberException() {
        super(ErrorCode.NOT_FOUND_SHARED_MEMBER_ENTITY);
    }
}
