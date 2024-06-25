package com.lezhin.webtoon.webtoonservice.query.application;

import com.lezhin.webtoon.webtoonservice.query.domain.AdultWebtoonView;
import com.lezhin.webtoon.webtoonservice.query.infrastructure.AdultWebtoonViewQueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdultWebtoonViewService {
    private final AdultWebtoonViewQueryDslRepository adultWebtoonViewQueryDslRepository;

    public List<AdultWebtoonView> getUsersWithThreeOrMoreAdultWebtoonViews() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        return adultWebtoonViewQueryDslRepository.findUsersWithThreeOrMoreAdultWebtoonViews(oneWeekAgo);
    }
}
