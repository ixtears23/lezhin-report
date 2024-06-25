package com.lezhin.webtoon.webtoonservice.webtoon.domain.exception;

import com.lezhin.webtoon.webtoonservice.core.exception.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WebtoonErrorCode implements BaseErrorCode {
    WEBTOON_NOT_FOUND("작품을 찾을 수 없습니다."),
    INVALID_COIN("가격은 0원 또는 100원에서 500원 사이여야 합니다.");

    private final String errorMessage;
}
