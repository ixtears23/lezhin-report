package com.lezhin.webtoon.webtoonservice.webtoon.application;

import com.lezhin.webtoon.webtoonservice.core.exception.BaseException;
import lombok.Getter;

@Getter
public class WebtoonException extends BaseException {
    private final WebtoonErrorCode errorCode;

    public WebtoonException(WebtoonErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
