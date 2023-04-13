package com.techeer.fmstudio.domain.banner.exception;

import com.techeer.fmstudio.global.error.exception.BusinessException;
import com.techeer.fmstudio.global.error.exception.ErrorCode;

public class NotFoundBannerException extends BusinessException {
    public NotFoundBannerException() {
        super(ErrorCode.NOT_FOUND_BANNER_ENTITY);
    }
}
