package com.techeer.fmstudio.global.error.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Global
    INTERNAL_SERVER_ERROR(500, "G001", "서버 오류"),
    INPUT_INVALID_VALUE(400, "G002", "잘못된 입력"),
    NOT_FOUND_BANNER_ENTITY(400, "B001", "존재하지 않는 Banner 입니다."),
    NOT_FOUND_MEMBER_ENTITY(400, "M001", "존재하지 않는 Member 입니다."),
    NOT_FOUND_TASK_ENTITY(400, "T001", "존재하지 않는 Task 입니다."),
    NOT_FOUND_SHARED_MEMBER_ENTITY(400, "S001", "존재하지 않는 SharedMember 입니다."),
    NOT_FOUND_TASK_NOTIFICATION_ENTITY(400, "T002", "존재하지 않는 TaskNotification 입니다.")
    ;

    private final int status;
    private final String code;
    private final String message;
}
