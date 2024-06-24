package com.lezhin.webtoon.webtoonservice.webtoon.infrastructure;

import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebtoonJpaRepository extends JpaRepository<Webtoon, Long> {
}
