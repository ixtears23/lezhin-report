package com.lezhin.webtoon.webtoonservice.webtoon.domain;

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
}
