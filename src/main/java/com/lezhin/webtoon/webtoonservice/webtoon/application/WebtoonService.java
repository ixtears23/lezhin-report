package com.lezhin.webtoon.webtoonservice.webtoon.application;

import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import com.lezhin.webtoon.webtoonservice.webtoon.infrastructure.WebtoonJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WebtoonService {
    private final WebtoonJpaRepository webtoonJpaRepository;

    public Webtoon validateWebtoonExists(Long webtoonId) {
        return webtoonJpaRepository.findById(webtoonId)
                .orElseThrow(() -> new WebtoonException(WebtoonErrorCode.WEBTOON_NOT_FOUND));
    }

}
