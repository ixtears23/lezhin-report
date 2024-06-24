package com.lezhin.webtoon.webtoonservice.user.application;

import com.lezhin.webtoon.webtoonservice.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Sql("/data/user-service-data.sql")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("존재하는 사용자 ID로 유저 검증 성공")
    void validateUserExistsSuccess() {
        Long userId = 1L;

        User result = userService.validateUserExists(userId);
        assertEquals("홍길동", result.getUserName());
    }

    @Test
    @DisplayName("존재하지 않는 사용자 ID로 유저 검증 실패")
    void validateUserExistsFailure() {
        Long userId = 99L;

        assertThrows(UserException.class, () -> userService.validateUserExists(userId), UserErrorCode.USER_NOT_FOUND.getErrorMessage());
    }
}
