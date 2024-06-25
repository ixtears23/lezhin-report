package com.lezhin.webtoon.webtoonservice.query.domain;

import com.lezhin.webtoon.webtoonservice.user.domain.Gender;
import com.lezhin.webtoon.webtoonservice.user.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AdultWebtoonView {
    private Long userId;
    private String userName;
    private Gender gender;
    private UserType type;
    private LocalDate registerDate;
}
