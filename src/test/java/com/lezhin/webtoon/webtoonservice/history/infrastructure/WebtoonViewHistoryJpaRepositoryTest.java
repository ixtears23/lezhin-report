package com.lezhin.webtoon.webtoonservice.history.infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class WebtoonViewHistoryJpaRepositoryTest {
    @Autowired
    private WebtoonViewHistoryJpaRepository webtoonViewHistoryJpaRepository;

    @Sql("/data/delete-webtoon-view-history.sql")
    @Transactional
    @DisplayName("userId로 webtoon_view_history 가 삭제되어야 한다.")
    @Test
    void deleteByUserIdTest() {
        final long userId = 1L;
        Integer webtoonViewHistoryCount = webtoonViewHistoryJpaRepository.countByUserId(userId);

        assertEquals(2, webtoonViewHistoryCount);

        webtoonViewHistoryJpaRepository.deleteByUserId(userId);

        assertEquals(0, webtoonViewHistoryJpaRepository.countByUserId(userId));
    }
}