package com.lezhin.webtoon.webtoonservice.evaluation.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

public class CreateEvaluation {

    @Builder
    public record Request(
            @NotNull(message = "사용자 ID는 필수 항목입니다.") Long userId,
            @NotNull(message = "웹툰 ID는 필수 항목입니다.") Long webtoonId,
            @NotNull(message = "평가 값은 필수 항목입니다.") Boolean evaluationValue,
            @Pattern(regexp = "^[a-zA-Z0-9가-힣\\s]*$", message = "댓글에 특수문자는 포함될 수 없습니다.") String comment
    ) {
    }
}
