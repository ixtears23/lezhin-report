package com.lezhin.webtoon.webtoonservice.webtoon.api;

import com.lezhin.webtoon.webtoonservice.core.dto.CommonResponse;
import com.lezhin.webtoon.webtoonservice.webtoon.application.WebtoonService;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/webtoons")
public class WebtoonController {
    private final WebtoonService webtoonService;

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
}
