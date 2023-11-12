package com.pastbin.authentication.dto.exception;

import lombok.Builder;

import java.time.Instant;

public class CanNotSendEmailException extends BaseException {
    @Builder
    public CanNotSendEmailException(String msg, Instant instant, Object objectCausedException, ErrorCode errorCode) {
        super(msg, instant, objectCausedException, errorCode);
    }
}
