package com.lezhin.webtoon.webtoonservice.evaluation.api;

import com.lezhin.webtoon.webtoonservice.core.dto.CommonResponse;
import com.lezhin.webtoon.webtoonservice.evaluation.api.dto.CreateEvaluation;
import com.lezhin.webtoon.webtoonservice.evaluation.application.EvaluationService;
import com.lezhin.webtoon.webtoonservice.evaluation.domain.Evaluation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "EvaluationController", description = "평가 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/evaluations")
public class EvaluationController {
    private final EvaluationService evaluationService;

    @Operation(summary = "특정 사용자가 해당 작품에 대해 평가('좋아요/싫어요', '댓글')을 할 수 있는 API", description = "사용자가 특정 작품에 대해 '좋아요/싫어요' 평가와 댓글을 추가합니다.")
    @PostMapping
    public ResponseEntity<CommonResponse<Evaluation>> createEvaluation(@Valid @RequestBody CreateEvaluation.Request request) {
        final Evaluation createdEvaluation = evaluationService.createEvaluation(request);
        return new ResponseEntity<>(
                new CommonResponse<>(
                        "CREATED",
                        "정상적으로 평가되었습니다.",
                        createdEvaluation
                ),
                HttpStatus.CREATED
        );
    }

}
