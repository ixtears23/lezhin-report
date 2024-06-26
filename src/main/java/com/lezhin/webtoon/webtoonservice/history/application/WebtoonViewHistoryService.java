package com.lezhin.webtoon.webtoonservice.history.application;

import com.lezhin.webtoon.webtoonservice.query.domain.AdultWebtoonView;
import com.lezhin.webtoon.webtoonservice.history.domain.WebtoonViewHistory;
import com.lezhin.webtoon.webtoonservice.history.infrastructure.WebtoonViewHistoryJpaRepository;
import com.lezhin.webtoon.webtoonservice.user.domain.event.UserDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WebtoonViewHistoryService {
    private final WebtoonViewHistoryJpaRepository webtoonViewHistoryJpaRepository;

    @Transactional(readOnly = true)
    public List<WebtoonViewHistory> getViewHistoryByWebtoonId(Long webtoonId) {
        return webtoonViewHistoryJpaRepository.findByWebtoonId(webtoonId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(WebtoonViewHistory viewHistory) {
        webtoonViewHistoryJpaRepository.save(viewHistory);
    }

    @EventListener
    public void handleUserDeletedEvent(UserDeletedEvent event) {
        webtoonViewHistoryJpaRepository.deleteByUserId(event.getUserId());
    }
}
