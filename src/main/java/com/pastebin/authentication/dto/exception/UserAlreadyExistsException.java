package com.pastebin.authentication.dto.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
public class UserAlreadyExistsException extends BaseException {
    @Builder
    public UserAlreadyExistsException(String msg, Instant instant, Object objectCausedException, ErrorCode errorCode) {
        super(msg, instant, objectCausedException, errorCode);
    }
}
