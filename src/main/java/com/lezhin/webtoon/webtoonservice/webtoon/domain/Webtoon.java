package com.lezhin.webtoon.webtoonservice.webtoon.domain;

import com.lezhin.webtoon.webtoonservice.webtoon.domain.exception.WebtoonErrorCode;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.exception.WebtoonException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Getter
@Entity
@Table(name = "webtoon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Webtoon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contentsName;
    private String author;
    private int coin;
    private LocalDate openDate;
    @Enumerated(EnumType.STRING)
    private WebtoonType type;

    public void updateCoin(int newCoin) {
        validateCoin(newCoin);
        this.coin = newCoin;
    }

    private void validateCoin(int coin) {
        if (coin != 0 && (coin < 100 || coin > 500)) {
            throw new WebtoonException(WebtoonErrorCode.INVALID_COIN);
        }
    }
}
