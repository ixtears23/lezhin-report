package com.lezhin.webtoon.webtoonservice.query.domain;

import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WebtoonEvaluationView {
    private Webtoon webtoon;
    private long likeCount;
    private long dislikeCount;
}
