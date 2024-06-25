package com.lezhin.webtoon.webtoonservice.webtoon.application;

import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.exception.WebtoonErrorCode;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.exception.WebtoonException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
@Sql("/data/webtoon-service-data.sql")
class WebtoonServiceTest {

    @Autowired
    private WebtoonService webtoonService;

    @Test
    @DisplayName("존재하는 웹툰 ID로 웹툰 검증 성공")
    void validateUserExistsSuccess() {
        Long webtoonId = 1L;

        Webtoon result = webtoonService.validateWebtoonExists(webtoonId);
        assertEquals("웹툰 제목1", result.getContentsName());
    }

    @Test
    @DisplayName("존재하지 않는 웹툰 ID로 웹툰 검증 실패")
    void validateUserExistsFailure() {
        Long webtoonId = 99L;

        assertThrows(WebtoonException.class, () -> webtoonService.validateWebtoonExists(webtoonId), WebtoonErrorCode.WEBTOON_NOT_FOUND.getErrorMessage());
    }
}