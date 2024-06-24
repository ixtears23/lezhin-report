package com.lezhin.webtoon.webtoonservice.evaluation.infrastructure;

import com.lezhin.webtoon.webtoonservice.evaluation.domain.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EvaluationJpaRepository extends JpaRepository<Evaluation, Long> {
    Optional<Evaluation> findByUserIdAndWebtoonId(Long userId, Long webtoonId);
}
