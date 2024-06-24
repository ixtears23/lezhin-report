package com.lezhin.webtoon.webtoonservice.evaluation.infrastructure;

import com.lezhin.webtoon.webtoonservice.evaluation.domain.Evaluation;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationJpaRepository extends JpaRepository<Evaluation, Long> {
    Optional<Evaluation> findByUserIdAndWebtoonId(Long userId, Long webtoonId);

    @Query("SELECT e.webtoon FROM Evaluation e GROUP BY e.webtoon ORDER BY SUM(CASE WHEN e.evaluationValue = true THEN 1 ELSE 0 END) DESC")
    List<Webtoon> findTop3ByOrderByLikesDesc();

    @Query("SELECT e.webtoon FROM Evaluation e GROUP BY e.webtoon ORDER BY SUM(CASE WHEN e.evaluationValue = false THEN 1 ELSE 0 END) DESC")
    List<Webtoon> findTop3ByOrderByDislikesDesc();
}
