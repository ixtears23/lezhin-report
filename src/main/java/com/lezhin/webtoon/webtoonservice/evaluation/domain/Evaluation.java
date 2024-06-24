package com.lezhin.webtoon.webtoonservice.evaluation.domain;

import com.lezhin.webtoon.webtoonservice.user.domain.User;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import jakarta.persistence.*;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "evaluation")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;
    @ManyToOne
    @JoinColumn(name = "webtoon_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Webtoon webtoon;
    private boolean evaluationValue;
    private String comment;

    public void evaluate(Boolean evaluationValue) {
        if (this.evaluationValue == evaluationValue) {
            throw new EvaluationException(EvaluationErrorCode.ALREADY_EVALUATED);
        }
        this.evaluationValue = evaluationValue;
    }
}
