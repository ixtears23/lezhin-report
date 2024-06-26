package com.lezhin.webtoon.webtoonservice.webtoon.api;

import com.lezhin.webtoon.webtoonservice.core.dto.CommonResponse;
import com.lezhin.webtoon.webtoonservice.webtoon.application.WebtoonService;
import com.lezhin.webtoon.webtoonservice.webtoon.application.dto.UpdateWebtoonPriceRequest;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "WebtoonController", description = "웹툰 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/webtoons")
public class WebtoonController {
    private final WebtoonService webtoonService;

    @Operation(summary = "웹툰 조회 API", description = "사용자 아이디와 웹툰 아이디로 웹툰 상세정보를 조회할 수 있습니다.")
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

    @Operation(summary = "유로/무료 변경 API", description = "특정 작품을 유로, 무료로 변경할 수 있습니다.")
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
