package com.lezhin.webtoon.webtoonservice.webtoon.domain;

import com.lezhin.webtoon.webtoonservice.webtoon.domain.exception.WebtoonException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WebtoonTest {
    private static Webtoon webtoon;

    @BeforeAll
    static void setUp() {
        webtoon = Webtoon.builder()
                .id(1L)
                .contentsName("웹툰 1")
                .author("작가 A")
                .coin(0)
                .openDate(LocalDate.now())
                .type(WebtoonType.NORMAL)
                .build();
    }

    @ParameterizedTest(name = "코인을 {0} 으로 업데이트 하면 결과는 {1}")
    @CsvSource({
            "0, true",
            "1, false",
            "-1, false",
            "99, false",
            "100, true",
            "101, true",
            "499, true",
            "500, true",
            "501, false"
    })
    @DisplayName("Test updateCoin with various values")
    void testUpdateCoin(int coin, boolean isSuccess) {
        if (isSuccess) {
            assertDoesNotThrow(() -> webtoon.updateCoin(coin));
            assertEquals(coin, webtoon.getCoin());
        } else {
            assertThrows(WebtoonException.class, () -> webtoon.updateCoin(coin));
        }
    }
}