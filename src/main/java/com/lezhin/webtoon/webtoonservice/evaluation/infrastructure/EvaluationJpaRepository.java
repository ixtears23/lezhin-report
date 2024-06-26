package com.lezhin.webtoon.webtoonservice.evaluation.infrastructure;

import com.lezhin.webtoon.webtoonservice.evaluation.domain.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationJpaRepository extends JpaRepository<Evaluation, Long> {
    Optional<Evaluation> findByUserIdAndWebtoonId(Long userId, Long webtoonId);
    List<Evaluation> findAllByUserId(Long userId);
    @Modifying
    @Query("""
        DELETE FROM Evaluation e
         WHERE e.user.id = :userId
    """)
    void deleteByUserId(Long userId);
}
