package com.lezhin.webtoon.webtoonservice.history.application;

import com.lezhin.webtoon.webtoonservice.query.domain.AdultWebtoonView;
import com.lezhin.webtoon.webtoonservice.history.domain.WebtoonViewHistory;
import com.lezhin.webtoon.webtoonservice.history.infrastructure.WebtoonViewHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WebtoonViewHistoryService {
    private final WebtoonViewHistoryJpaRepository webtoonViewHistoryJpaRepository;

    public List<WebtoonViewHistory> getViewHistoryByWebtoonId(Long webtoonId) {
        return webtoonViewHistoryJpaRepository.findByWebtoonId(webtoonId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(WebtoonViewHistory viewHistory) {
        webtoonViewHistoryJpaRepository.save(viewHistory);
    }

    public List<AdultWebtoonView> getUsersWithThreeOrMoreAdultWebtoonViews() {
        return null;
    }
}
