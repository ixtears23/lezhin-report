package com.lezhin.webtoon.webtoonservice.user.application;

import com.lezhin.webtoon.webtoonservice.core.exception.BaseException;
import lombok.Getter;

@Getter
public class UserException extends BaseException {
    private final UserErrorCode errorCode;

    public UserException(UserErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
