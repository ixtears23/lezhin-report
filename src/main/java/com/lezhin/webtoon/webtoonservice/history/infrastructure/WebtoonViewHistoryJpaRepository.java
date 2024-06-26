package com.lezhin.webtoon.webtoonservice.history.infrastructure;

import com.lezhin.webtoon.webtoonservice.history.domain.WebtoonViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WebtoonViewHistoryJpaRepository extends JpaRepository<WebtoonViewHistory, Long> {
    @Query("""
        SELECT wvh
          FROM WebtoonViewHistory wvh
          JOIN FETCH wvh.user
          JOIN FETCH wvh.webtoon
         WHERE wvh.webtoon.id = :webtoonId
    """)
    List<WebtoonViewHistory> findByWebtoonId(Long webtoonId);
    Integer countByUserId(Long userId);
    @Modifying
    @Query("""
        DELETE FROM WebtoonViewHistory wvh
         WHERE wvh.user.id = :userId
    """)
    void deleteByUserId(Long userId);

}
