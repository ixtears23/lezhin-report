package com.lezhin.webtoon.webtoonservice.core.dto;

public record CommonResponse<T>(String code,
                                String message,
                                T data) {}