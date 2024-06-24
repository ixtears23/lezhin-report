package com.lezhin.webtoon.webtoonservice.webtoon.api;

import com.lezhin.webtoon.webtoonservice.core.dto.CommonResponse;
import com.lezhin.webtoon.webtoonservice.webtoon.application.WebtoonService;
import com.lezhin.webtoon.webtoonservice.history.application.WebtoonViewHistoryService;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import com.lezhin.webtoon.webtoonservice.history.domain.WebtoonViewHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/webtoons")
public class WebtoonController {
    private final WebtoonService webtoonService;
    private final WebtoonViewHistoryService webtoonViewHistoryService;

    @GetMapping("/{webtoonId}/view")
    public ResponseEntity<CommonResponse<Webtoon>> viewWebtoon(@RequestParam Long userId,
                                                               @PathVariable Long webtoonId) {
        Webtoon webtoon = webtoonService.viewWebtoon(userId, webtoonId);
        return new ResponseEntity<>(
                new CommonResponse<>(
                        "SUCCESS",
                        "웹툰 조회 성공",
                        webtoon
                ),
                HttpStatus.OK
        );
    }

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
