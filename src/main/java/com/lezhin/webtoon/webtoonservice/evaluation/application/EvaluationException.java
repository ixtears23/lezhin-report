package com.lezhin.webtoon.webtoonservice.evaluation.application;

import lombok.Getter;

@Getter
public class EvaluationException extends RuntimeException {
    private final EvaluationErrorCode errorCode;

    public EvaluationException(EvaluationErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
