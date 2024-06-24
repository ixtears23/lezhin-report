package com.lezhin.webtoon.webtoonservice.evaluation.application;

import com.lezhin.webtoon.webtoonservice.evaluation.api.dto.CreateEvaluation;
import com.lezhin.webtoon.webtoonservice.evaluation.domain.Evaluation;
import com.lezhin.webtoon.webtoonservice.user.domain.User;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import org.springframework.stereotype.Component;

@Component
public class EvaluationMapper {

    public Evaluation toEvaluation(CreateEvaluation.Request request) {
        return Evaluation.builder()
                .user(User.builder()
                        .id(request.userId())
                        .build())
                .webtoon(Webtoon.builder()
                        .id(request.webtoonId())
                        .build())
                .evaluationValue(request.evaluationValue())
                .comment(request.comment())
                .build();
    }
}
