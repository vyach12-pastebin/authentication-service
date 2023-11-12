package com.pastbin.authentication.dto.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
public class UserNotFoundException extends BaseException {
    @Builder
    UserNotFoundException(String msg, Instant instant, Object objectCausedException, ErrorCode errorCode) {
        super(msg, instant, objectCausedException, errorCode);
    }
}
