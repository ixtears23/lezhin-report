package com.lezhin.webtoon.webtoonservice.query.application;

import com.lezhin.webtoon.webtoonservice.query.domain.WebtoonEvaluationView;
import com.lezhin.webtoon.webtoonservice.query.infrastructure.WebtoonEvaluationQueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WebtoonEvaluationService {
    private final WebtoonEvaluationQueryDslRepository webtoonEvaluationQueryDslRepository;

    @Transactional(readOnly = true)
    public List<WebtoonEvaluationView> getTopLikedWebtoons() {
        return webtoonEvaluationQueryDslRepository.findTop3ByOrderByLikesDesc();
    }

    @Transactional(readOnly = true)
    public List<WebtoonEvaluationView> getTopDislikedWebtoons() {
        return webtoonEvaluationQueryDslRepository.findTop3ByOrderByDislikesDesc();
    }
}
