package com.lezhin.webtoon.webtoonservice.webtoon.api;

import com.lezhin.webtoon.webtoonservice.core.dto.CommonResponse;
import com.lezhin.webtoon.webtoonservice.webtoon.application.WebtoonService;
import com.lezhin.webtoon.webtoonservice.webtoon.application.dto.UpdateWebtoonPriceRequest;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @PatchMapping("/{webtoonId}/coin")
    public ResponseEntity<CommonResponse<Webtoon>> updateWebtoonPrice(
            @PathVariable Long webtoonId,
            @Validated @RequestBody UpdateWebtoonPriceRequest request) {
        Webtoon updatedWebtoon = webtoonService.updateWebtoonPrice(webtoonId, request);
        return new ResponseEntity<>(
                new CommonResponse<>(
                        "UPDATED",
                        "웹툰 가격이 정상적으로 변경되었습니다.",
                        updatedWebtoon
                ),
                HttpStatus.OK
        );
    }
}
