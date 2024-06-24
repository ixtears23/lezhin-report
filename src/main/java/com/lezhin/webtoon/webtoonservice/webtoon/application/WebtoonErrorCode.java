package com.lezhin.webtoon.webtoonservice.webtoon.application;

import com.lezhin.webtoon.webtoonservice.core.exception.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WebtoonErrorCode implements BaseErrorCode {
    WEBTOON_NOT_FOUND("작품을 찾을 수 없습니다.");

    private final String errorMessage;
}
