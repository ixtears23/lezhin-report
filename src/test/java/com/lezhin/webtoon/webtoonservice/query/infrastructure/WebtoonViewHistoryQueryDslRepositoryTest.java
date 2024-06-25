package com.lezhin.webtoon.webtoonservice.query.infrastructure;

import com.lezhin.webtoon.webtoonservice.core.config.QueryDslConfig;
import com.lezhin.webtoon.webtoonservice.query.domain.AdultWebtoonView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({QueryDslConfig.class, WebtoonViewHistoryQueryDslRepository.class})
class WebtoonViewHistoryQueryDslRepositoryTest {

    @Autowired
    private WebtoonViewHistoryQueryDslRepository webtoonViewHistoryQueryDslRepository;

    @Test
    @DisplayName("성인 작품을 3개 이상 조회한 사용자가 없는 경우")
    @Sql(scripts = "/data/webtoon-view-history-empty-data.sql")
    @Transactional
    void findUsersWithThreeOrMoreAdultWebtoonViewsNoUsers() {
        List<AdultWebtoonView> result = webtoonViewHistoryQueryDslRepository.findUsersWithThreeOrMoreAdultWebtoonViews(LocalDateTime.now().minusWeeks(1));

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("성인 작품을 3개 이상 조회한 사용자가 1명인 경우")
    @Sql(scripts = "/data/webtoon-view-history-one-user.sql")
    @Transactional
    void findUsersWithThreeOrMoreAdultWebtoonViewsOneUser() {
        List<AdultWebtoonView> result = webtoonViewHistoryQueryDslRepository.findUsersWithThreeOrMoreAdultWebtoonViews(LocalDateTime.now().minusWeeks(1));

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUserName()).isEqualTo("홍길동");
    }

    @Test
    @DisplayName("성인 작품을 3개 이상 조회한 사용자가 3명인 경우")
    @Sql(scripts = "/data/webtoon-view-history-three-users.sql")
    @Transactional
    void findUsersWithThreeOrMoreAdultWebtoonViewsThreeUsers() {
        List<AdultWebtoonView> result = webtoonViewHistoryQueryDslRepository.findUsersWithThreeOrMoreAdultWebtoonViews(LocalDateTime.now().minusWeeks(1));

        assertThat(result).hasSize(3);
        assertThat(result).extracting("userName").containsExactlyInAnyOrder("홍길동", "이철수", "박영희");
    }
}
