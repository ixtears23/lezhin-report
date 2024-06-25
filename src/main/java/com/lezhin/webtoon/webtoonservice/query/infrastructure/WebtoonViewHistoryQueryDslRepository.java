package com.lezhin.webtoon.webtoonservice.query.infrastructure;

import com.lezhin.webtoon.webtoonservice.query.domain.AdultWebtoonView;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.WebtoonType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.lezhin.webtoon.webtoonservice.history.domain.QWebtoonViewHistory.webtoonViewHistory;
import static com.lezhin.webtoon.webtoonservice.webtoon.domain.QWebtoon.webtoon;

@RequiredArgsConstructor
@Repository
public class WebtoonViewHistoryQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public List<AdultWebtoonView> findUsersWithThreeOrMoreAdultWebtoonViews(LocalDateTime oneWeekAgo) {
        return queryFactory
                .select(Projections.constructor(
                        AdultWebtoonView.class,
                        webtoonViewHistory.user.id,
                        webtoonViewHistory.user.userName,
                        webtoonViewHistory.user.gender,
                        webtoonViewHistory.user.type,
                        webtoonViewHistory.user.registerDate
                ))
                .from(webtoonViewHistory)
                .join(webtoonViewHistory.webtoon, webtoon)
                .where(
                        webtoon.type.eq(WebtoonType.ADULT),
                        webtoonViewHistory.viewDate.after(oneWeekAgo)
                )
                .groupBy(webtoonViewHistory.user.id)
                .having(webtoonViewHistory.webtoon.countDistinct().goe(3))
                .fetch();
    }
}
