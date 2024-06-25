package com.lezhin.webtoon.webtoonservice.query.infrastructure;

import com.lezhin.webtoon.webtoonservice.core.config.QueryDslConfig;
import com.lezhin.webtoon.webtoonservice.query.domain.WebtoonEvaluationView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({QueryDslConfig.class, WebtoonEvaluationQueryDslRepository.class})
class WebtoonEvaluationQueryDslRepositoryTest {
    @Autowired
    private WebtoonEvaluationQueryDslRepository webtoonEvaluationQueryDslRepository;

    @Test
    @DisplayName("좋아요가 가장 많은 작품 3개 조회 성공 케이스 - 3개")
    @Sql(scripts = "/data/webtoon-evaluation-data01.sql")
    @Transactional
    void findTop3ByOrderByLikesDescSuccess1() {
        List<WebtoonEvaluationView> result = webtoonEvaluationQueryDslRepository.findTop3ByOrderByLikesDesc();

        assertThat(result).hasSize(3);
        assertThat(result.get(0).getWebtoon().getContentsName()).isEqualTo("Webtoon3");
        assertThat(result.get(1).getWebtoon().getContentsName()).isEqualTo("Webtoon2");
        assertThat(result.get(2).getWebtoon().getContentsName()).isEqualTo("Webtoon1");
    }

    @Test
    @DisplayName("싫어요가 가장 많은 작품 3개 조회 성공 케이스 - 1개")
    @Sql(scripts = "/data/webtoon-evaluation-data01.sql")
    @Transactional
    void findTop3ByOrderByDislikesDescSuccess1() {
        List<WebtoonEvaluationView> result = webtoonEvaluationQueryDslRepository.findTop3ByOrderByDislikesDesc();

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getWebtoon().getContentsName()).isEqualTo("Webtoon1");
    }


    @Test
    @DisplayName("좋아요가 가장 많은 작품 3개 조회 성공 케이스 - 2개")
    @Sql(scripts = "/data/webtoon-evaluation-data02.sql")
    @Transactional
    void findTop3ByOrderByLikesDescSuccess2() {
        List<WebtoonEvaluationView> result = webtoonEvaluationQueryDslRepository.findTop3ByOrderByLikesDesc();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getWebtoon().getContentsName()).isEqualTo("Webtoon3");
        assertThat(result.get(1).getWebtoon().getContentsName()).isEqualTo("Webtoon2");
    }

    @Test
    @DisplayName("싫어요가 가장 많은 작품 3개 조회 성공 케이스 - 3개")
    @Sql(scripts = "/data/webtoon-evaluation-data03.sql")
    @Transactional
    void findTop3ByOrderByDislikesDescSuccess2() {
        List<WebtoonEvaluationView> result = webtoonEvaluationQueryDslRepository.findTop3ByOrderByDislikesDesc();

        assertThat(result).hasSize(3);
        assertThat(result.get(0).getWebtoon().getContentsName()).isEqualTo("Webtoon4");
        assertThat(result.get(1).getWebtoon().getContentsName()).isEqualTo("Webtoon3");
        assertThat(result.get(2).getWebtoon().getContentsName()).isEqualTo("Webtoon2");
    }

    @Test
    @DisplayName("좋아요가 없는 경우 조회 실패 케이스")
    @Sql(scripts = "/data/webtoon-evaluation-empty-data.sql")
    @Transactional
    void findTop3ByOrderByLikesDescNoLikes() {
        List<WebtoonEvaluationView> result = webtoonEvaluationQueryDslRepository.findTop3ByOrderByLikesDesc();

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("싫어요가 없는 경우 조회 실패 케이스")
    @Sql(scripts = "/data/webtoon-evaluation-empty-data.sql")
    @Transactional
    void findTop3ByOrderByDislikesDescNoDislikes() {
        List<WebtoonEvaluationView> result = webtoonEvaluationQueryDslRepository.findTop3ByOrderByDislikesDesc();

        assertThat(result).isEmpty();
    }
}