package com.lezhin.webtoon.webtoonservice.webtoon.api;

import com.lezhin.webtoon.webtoonservice.evaluation.application.EvaluationService;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/webtoons")
@RequiredArgsConstructor
public class WebtoonEvaluationController {

    private final EvaluationService evaluationService;

    @GetMapping("/top-liked")
    public ResponseEntity<List<Webtoon>> getTopLikedWebtoons() {
        List<Webtoon> topLikedWebtoons = evaluationService.getTopLikedWebtoons();
        return ResponseEntity.ok(topLikedWebtoons);
    }

    @GetMapping("/top-disliked")
    public ResponseEntity<List<Webtoon>> getTopDislikedWebtoons() {
        List<Webtoon> topDislikedWebtoons = evaluationService.getTopDislikedWebtoons();
        return ResponseEntity.ok(topDislikedWebtoons);
    }
}