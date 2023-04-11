package com.techeer.fmstudio.domain.member.exception;

import com.techeer.fmstudio.global.error.exception.BusinessException;
import com.techeer.fmstudio.global.error.exception.ErrorCode;

public class NotFoundMemberException extends BusinessException {
    public NotFoundMemberException() {
        super(ErrorCode.NOT_FOUND_MEMBER_ENTITY);
    }
}
