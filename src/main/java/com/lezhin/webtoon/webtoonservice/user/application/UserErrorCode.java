package com.lezhin.webtoon.webtoonservice.user.application;

import com.lezhin.webtoon.webtoonservice.core.exception.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.");

    private final String errorMessage;
}
