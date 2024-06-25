package com.lezhin.webtoon.webtoonservice.query.infrastructure;

import com.lezhin.webtoon.webtoonservice.query.domain.WebtoonEvaluationView;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.lezhin.webtoon.webtoonservice.evaluation.domain.QEvaluation.evaluation;
import static com.lezhin.webtoon.webtoonservice.webtoon.domain.QWebtoon.webtoon;


@RequiredArgsConstructor
@Repository
public class WebtoonEvaluationQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public List<WebtoonEvaluationView> findTop3ByOrderByLikesDesc() {
        return queryFactory
                .select(Projections.constructor(
                        WebtoonEvaluationView.class,
                        webtoon,
                        new CaseBuilder()
                                .when(evaluation.evaluationValue.isTrue())
                                .then(1L)
                                .otherwise(0L)
                                .sumLong().as("likeCount"),
                        new CaseBuilder()
                                .when(evaluation.evaluationValue.isFalse())
                                .then(1L)
                                .otherwise(0L)
                                .sumLong().as("dislikeCount")
                ))
                .from(evaluation)
                .join(evaluation.webtoon, webtoon)
                .groupBy(webtoon.id)
                .having(new CaseBuilder()
                        .when(evaluation.evaluationValue.isTrue())
                        .then(1L)
                        .otherwise(0L)
                        .sumLong().gt(0))
                .orderBy(new CaseBuilder()
                        .when(evaluation.evaluationValue.isTrue())
                        .then(1L)
                        .otherwise(0L)
                        .sumLong().desc(), webtoon.id.desc())
                .limit(3)
                .fetch();
    }

    public List<WebtoonEvaluationView> findTop3ByOrderByDislikesDesc() {
        return queryFactory
                .select(Projections.constructor(
                        WebtoonEvaluationView.class,
                        webtoon,
                        new CaseBuilder()
                                .when(evaluation.evaluationValue.isTrue())
                                .then(1L)
                                .otherwise(0L)
                                .sumLong().as("likeCount"),
                        new CaseBuilder()
                                .when(evaluation.evaluationValue.isFalse())
                                .then(1L)
                                .otherwise(0L)
                                .sumLong().as("dislikeCount")
                ))
                .from(evaluation)
                .join(evaluation.webtoon, webtoon)
                .groupBy(webtoon.id)
                .having(new CaseBuilder()
                        .when(evaluation.evaluationValue.isFalse())
                        .then(1L)
                        .otherwise(0L)
                        .sumLong().gt(0))
                .orderBy(new CaseBuilder()
                        .when(evaluation.evaluationValue.isFalse())
                        .then(1L)
                        .otherwise(0L)
                        .sumLong().desc(), webtoon.id.desc())
                .limit(3)
                .fetch();
    }
}