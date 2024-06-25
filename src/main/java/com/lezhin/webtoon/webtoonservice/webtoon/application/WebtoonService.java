package com.lezhin.webtoon.webtoonservice.webtoon.application;

import com.lezhin.webtoon.webtoonservice.history.application.WebtoonViewHistoryService;
import com.lezhin.webtoon.webtoonservice.history.domain.WebtoonViewHistory;
import com.lezhin.webtoon.webtoonservice.user.application.UserService;
import com.lezhin.webtoon.webtoonservice.user.domain.User;
import com.lezhin.webtoon.webtoonservice.webtoon.application.dto.UpdateWebtoonPriceRequest;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.exception.WebtoonErrorCode;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.exception.WebtoonException;
import com.lezhin.webtoon.webtoonservice.webtoon.infrastructure.WebtoonJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class WebtoonService {
    private final WebtoonJpaRepository webtoonJpaRepository;
    private final WebtoonViewHistoryService webtoonViewHistoryService;
    private final UserService userService;

    public Webtoon validateWebtoonExists(Long webtoonId) {
        return webtoonJpaRepository.findById(webtoonId)
                .orElseThrow(() -> new WebtoonException(WebtoonErrorCode.WEBTOON_NOT_FOUND));
    }

    @Transactional(rollbackFor = Exception.class)
    public Webtoon viewWebtoon(Long userId, Long webtoonId) {
        Webtoon webtoon = validateWebtoonExists(webtoonId);
        User user = userService.validateUserExists(userId);

        WebtoonViewHistory viewHistory = WebtoonViewHistory.builder()
                .user(user)
                .webtoon(webtoon)
                .viewDate(LocalDateTime.now())
                .build();
        webtoonViewHistoryService.create(viewHistory);

        return webtoon;
    }

    @Transactional(rollbackFor = Exception.class)
    public Webtoon updateWebtoonPrice(Long webtoonId, UpdateWebtoonPriceRequest request) {
        Webtoon webtoon = webtoonJpaRepository.findById(webtoonId)
                .orElseThrow(() -> new WebtoonException(WebtoonErrorCode.WEBTOON_NOT_FOUND));

        webtoon.updateCoin(request.coin());
        return webtoon;
    }
}
