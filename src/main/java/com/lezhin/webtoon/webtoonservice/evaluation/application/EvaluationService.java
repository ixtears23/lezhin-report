package com.lezhin.webtoon.webtoonservice.evaluation.application;

import com.lezhin.webtoon.webtoonservice.evaluation.api.dto.CreateEvaluation;
import com.lezhin.webtoon.webtoonservice.evaluation.domain.Evaluation;
import com.lezhin.webtoon.webtoonservice.evaluation.domain.EvaluationErrorCode;
import com.lezhin.webtoon.webtoonservice.evaluation.domain.EvaluationException;
import com.lezhin.webtoon.webtoonservice.evaluation.infrastructure.EvaluationJpaRepository;
import com.lezhin.webtoon.webtoonservice.query.infrastructure.WebtoonEvaluationQueryDslRepository;
import com.lezhin.webtoon.webtoonservice.user.application.UserService;
import com.lezhin.webtoon.webtoonservice.user.domain.event.UserDeletedEvent;
import com.lezhin.webtoon.webtoonservice.webtoon.application.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EvaluationService {
    private final EvaluationJpaRepository evaluationJpaRepository;
    private final WebtoonEvaluationQueryDslRepository webtoonEvaluationQueryDslRepository;
    private final EvaluationMapper evaluationMapper;
    private final UserService userService;
    private final WebtoonService webtoonService;

    @Transactional(rollbackFor = Exception.class)
    public Evaluation createEvaluation(CreateEvaluation.Request request) {
        userService.validateUserExists(request.userId());
        webtoonService.validateWebtoonExists(request.webtoonId());

        // 기존 평가 확인
        Optional<Evaluation> existingEvaluation = evaluationJpaRepository.findByUserIdAndWebtoonId(request.userId(), request.webtoonId());
        if (existingEvaluation.isPresent()) {
            Evaluation evaluation = existingEvaluation.get();

            if (evaluation.isEvaluationValue() == request.evaluationValue()) {
                throw new EvaluationException(EvaluationErrorCode.ALREADY_EVALUATED);
            }

            evaluation.evaluate(request.evaluationValue());
            return evaluationJpaRepository.save(evaluation);
        }

        final Evaluation evaluation = evaluationMapper.toEvaluation(request);

        return evaluationJpaRepository.save(evaluation);
    }

    @EventListener
    public void handleUserDeletedEvent(UserDeletedEvent event) {
        evaluationJpaRepository.deleteByUserId(event.getUserId());
    }

}
