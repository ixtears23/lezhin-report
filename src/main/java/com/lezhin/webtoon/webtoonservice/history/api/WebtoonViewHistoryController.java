package com.lezhin.webtoon.webtoonservice.history.api;

import com.lezhin.webtoon.webtoonservice.core.dto.CommonResponse;
import com.lezhin.webtoon.webtoonservice.history.application.WebtoonViewHistoryService;
import com.lezhin.webtoon.webtoonservice.history.domain.WebtoonViewHistory;
import com.lezhin.webtoon.webtoonservice.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/webtoons")
@RestController
public class WebtoonViewHistoryController {
    private final WebtoonViewHistoryService webtoonViewHistoryService;

    @GetMapping("/{webtoonId}/view-history")
    public ResponseEntity<CommonResponse<List<WebtoonViewHistory>>> getViewHistory(@PathVariable Long webtoonId) {
        List<WebtoonViewHistory> viewHistory = webtoonViewHistoryService.getViewHistoryByWebtoonId(webtoonId);
        return new ResponseEntity<>(
                new CommonResponse<>(
                        "SUCCESS",
                        "조회 이력을 성공적으로 가져왔습니다.",
                        viewHistory
                ),
                HttpStatus.OK
        );
    }

}
