package com.lezhin.webtoon.webtoonservice.evaluation.domain;

import com.lezhin.webtoon.webtoonservice.user.domain.Gender;
import com.lezhin.webtoon.webtoonservice.user.domain.User;
import com.lezhin.webtoon.webtoonservice.user.domain.UserType;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EvaluationTest {
    private static Evaluation.EvaluationBuilder evaluationBuilder;
    private static User user;
    private static Webtoon webtoon;

    @BeforeAll
    static void setUp() {
        user = User.builder()
                .id(1L)
                .userName("홍길동")
                .gender(Gender.MALE)
                .type(UserType.NORMAL)
                .registerDate(LocalDate.now())
                .build();

        webtoon = Webtoon.builder()
                .id(1L)
                .contentsName("웹툰 제목")
                .author("작가")
                .coin(100)
                .openDate(LocalDate.now())
                .build();

        evaluationBuilder = Evaluation.builder()
                .id(1L)
                .user(user)
                .webtoon(webtoon)
                .comment("재밌어요");

    }

    @Test
    @DisplayName("좋아요 -> 싫어요 성공 테스트")
    void changeDisLikeEvaluationSuccess() {
        Evaluation evaluation = evaluationBuilder.evaluationValue(true)
                .build();

        evaluation.evaluate(false);
        assertFalse(evaluation.isEvaluationValue());
    }

    @Test
    @DisplayName("싫어요 -> 좋아요 성공 테스트")
    void changeLikeEvaluationSuccess() {
        Evaluation evaluation = evaluationBuilder.evaluationValue(false)
                .build();

        evaluation.evaluate(true);
        assertTrue(evaluation.isEvaluationValue());
    }

    @Test
    @DisplayName("좋아요 -> 좋아요 실패 테스트")
    void changeEvaluationFailureSameValue() {
        Evaluation evaluation = evaluationBuilder.evaluationValue(true)
                .build();

        EvaluationException exception = assertThrows(EvaluationException.class, () -> {
            evaluation.evaluate(true);
        });
        assertEquals(EvaluationErrorCode.ALREADY_EVALUATED, exception.getErrorCode());
    }

    @Test
    @DisplayName("싫어요 -> 싫어요 실패 테스트")
    void changeEvaluationFailureSameValueDislike() {
        Evaluation evaluation = evaluationBuilder.evaluationValue(false)
                .build();

        EvaluationException exception = assertThrows(EvaluationException.class, () -> {
            evaluation.evaluate(false);
        });
        assertEquals(EvaluationErrorCode.ALREADY_EVALUATED, exception.getErrorCode());
    }
}
