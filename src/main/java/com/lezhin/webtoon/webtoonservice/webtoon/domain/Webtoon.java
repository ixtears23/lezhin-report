package com.lezhin.webtoon.webtoonservice.webtoon.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
}
