package com.lezhin.webtoon.webtoonservice.evaluation.infrastructure;

import com.lezhin.webtoon.webtoonservice.evaluation.domain.Evaluation;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface WebtoonEvaluationRepository extends Repository<Evaluation, Long> {

    @Query("""
        SELECT e.webtoon
          FROM Evaluation e
      GROUP BY e.webtoon
      ORDER BY SUM(CASE
                    WHEN e.evaluationValue = 1
                        THEN 1
                        ELSE 0
                    END) DESC
    """)
    List<Webtoon> findTop3ByOrderByLikesDesc();

    @Query("""
        SELECT e.webtoon
          FROM Evaluation e
      GROUP BY e.webtoon
      ORDER BY SUM(CASE
                    WHEN e.evaluationValue = 0
                        THEN 1 ELSE 0
                    END) DESC
    """)
    List<Webtoon> findTop3ByOrderByDislikesDesc();
}