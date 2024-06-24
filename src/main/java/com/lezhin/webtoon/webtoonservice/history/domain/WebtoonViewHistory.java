package com.lezhin.webtoon.webtoonservice.history.domain;

import com.lezhin.webtoon.webtoonservice.user.domain.User;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "webtoon_view_history")
public class WebtoonViewHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;
    @ManyToOne
    @JoinColumn(name = "webtoon_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Webtoon webtoon;
    private LocalDateTime viewDate;
}
