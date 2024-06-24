package com.lezhin.webtoon.webtoonservice.core.exception;

public abstract class BaseException extends RuntimeException {
    public abstract BaseErrorCode getErrorCode();

    protected BaseException(String message) {
        super(message);
    }
}