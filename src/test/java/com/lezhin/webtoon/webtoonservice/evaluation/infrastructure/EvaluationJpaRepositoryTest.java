package com.lezhin.webtoon.webtoonservice.evaluation.infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EvaluationJpaRepositoryTest {
    @Autowired
    private EvaluationJpaRepository evaluationJpaRepository;

    @Sql("/data/delete-evaluation.sql")
    @Transactional
    @DisplayName("userId로 webtoon_view_history 가 삭제되어야 한다.")
    @Test
    void deleteByUserIdTest() {
        final long userId = 1L;
        Integer evaluationCount = evaluationJpaRepository.findAllByUserId(userId).size();

        assertEquals(2, evaluationCount);

        evaluationJpaRepository.deleteByUserId(userId);

        assertEquals(0, evaluationJpaRepository.findAllByUserId(userId).size());
    }
}