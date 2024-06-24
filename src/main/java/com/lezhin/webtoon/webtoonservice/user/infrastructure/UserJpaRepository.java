package com.lezhin.webtoon.webtoonservice.user.infrastructure;

import com.lezhin.webtoon.webtoonservice.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
