package com.example.later.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LaterApplicationException extends RuntimeException {
    public LaterApplicationException(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }
    public LaterApplicationException(String message) {
        super(message);
        log.error(message);
    }
}
