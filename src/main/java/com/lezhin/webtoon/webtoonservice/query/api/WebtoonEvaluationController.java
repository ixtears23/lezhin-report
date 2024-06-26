package com.lezhin.webtoon.webtoonservice.query.api;

import com.lezhin.webtoon.webtoonservice.core.dto.CommonResponse;
import com.lezhin.webtoon.webtoonservice.query.application.WebtoonEvaluationService;
import com.lezhin.webtoon.webtoonservice.query.domain.WebtoonEvaluationView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "WebtoonEvaluationController", description = "웹툰 평가 조회 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/evaluations")
public class WebtoonEvaluationController {
    private final WebtoonEvaluationService webtoonEvaluationService;

    @Operation(summary = "'좋아요'가 가장 많은 작품 3개 조회", description = "좋아요가 가장 많은 상위 3개의 작품을 조회합니다.")
    @GetMapping("/top-liked-webtoons")
    public ResponseEntity<CommonResponse<List<WebtoonEvaluationView>>> getTopLikedWebtoons() {
        List<WebtoonEvaluationView> topLikedWebtoons = webtoonEvaluationService.getTopLikedWebtoons();
        return ResponseEntity.ok(new CommonResponse<>(
                "SUCCESS",
                "좋아요가 가장 많은 웹툰 3개 조회 성공",
                topLikedWebtoons
        ));
    }

    @Operation(summary = "'싫어요'가 가장 많은 작품 3개 조회", description = "싫어요가 가장 많은 상위 3개의 작품을 조회합니다.")
    @GetMapping("/top-disliked-webtoons")
    public ResponseEntity<CommonResponse<List<WebtoonEvaluationView>>> getTopDislikedWebtoons() {
        List<WebtoonEvaluationView> topDislikedWebtoons = webtoonEvaluationService.getTopDislikedWebtoons();
        return ResponseEntity.ok(new CommonResponse<>(
                "SUCCESS",
                "싫어요가 가장 많은 웹툰 3개 조회 성공",
                topDislikedWebtoons
        ));
    }
}
