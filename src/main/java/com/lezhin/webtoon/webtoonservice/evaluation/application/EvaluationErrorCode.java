package com.lezhin.webtoon.webtoonservice.evaluation.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EvaluationErrorCode {
    EVALUATION_ERROR_001("사용자를 찾을 수 없습니다."),
    EVALUATION_ERROR_002("작품을 찾을 수 없습니다."),
    EVALUATION_ERROR_003("해당 작품에 이미 평가를 하셨습니다.");

    private final String errorMessage;
}
