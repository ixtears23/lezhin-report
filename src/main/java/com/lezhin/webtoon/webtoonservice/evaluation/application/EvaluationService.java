package com.lezhin.webtoon.webtoonservice.evaluation.application;

import com.lezhin.webtoon.webtoonservice.evaluation.api.dto.CreateEvaluation;
import com.lezhin.webtoon.webtoonservice.evaluation.domain.Evaluation;
import com.lezhin.webtoon.webtoonservice.evaluation.infrastructure.EvaluationJpaRepository;
import com.lezhin.webtoon.webtoonservice.user.application.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EvaluationService {
    private final EvaluationJpaRepository evaluationJpaRepository;
    private final EvaluationMapper evaluationMapper;
    private final UserService userService;

    @Transactional(rollbackOn = Exception.class)
    public Evaluation createEvaluation(CreateEvaluation.Request request) {
        userService.validateUserExists(request.userId());

        final Evaluation evaluation = evaluationMapper.toEvaluation(request);


        return evaluationJpaRepository.save(evaluation);
    }
}
