package com.lezhin.webtoon.webtoonservice.evaluation.domain;

import com.lezhin.webtoon.webtoonservice.core.exception.BaseException;
import lombok.Getter;

@Getter
public class EvaluationException extends BaseException {
    private final EvaluationErrorCode errorCode;

    public EvaluationException(EvaluationErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
