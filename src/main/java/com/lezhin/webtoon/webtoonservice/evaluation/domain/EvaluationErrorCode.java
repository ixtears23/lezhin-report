package com.lezhin.webtoon.webtoonservice.evaluation.domain;

import com.lezhin.webtoon.webtoonservice.core.exception.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EvaluationErrorCode implements BaseErrorCode {
    ALREADY_EVALUATED("해당 작품에 이미 평가를 하셨습니다.");

    private final String errorMessage;
}
