package com.lezhin.webtoon.webtoonservice.evaluation.api;

import com.lezhin.webtoon.webtoonservice.core.dto.CommonResponse;
import com.lezhin.webtoon.webtoonservice.core.dto.ArgumentsNotValidResponse;
import com.lezhin.webtoon.webtoonservice.core.dto.ErrorResponse;
import com.lezhin.webtoon.webtoonservice.evaluation.api.dto.CreateEvaluation;
import com.lezhin.webtoon.webtoonservice.evaluation.application.EvaluationException;
import com.lezhin.webtoon.webtoonservice.evaluation.application.EvaluationService;
import com.lezhin.webtoon.webtoonservice.evaluation.domain.Evaluation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/evaluations")
public class EvaluationController {
    private final EvaluationService evaluationService;

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

    @ExceptionHandler(EvaluationException.class)
    public ResponseEntity<ErrorResponse> handleException(EvaluationException exception) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        exception.getErrorCode().name(),
                        exception.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );

    }
}