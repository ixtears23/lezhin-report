package com.lezhin.webtoon.webtoonservice.user.application;

import com.lezhin.webtoon.webtoonservice.user.domain.User;
import com.lezhin.webtoon.webtoonservice.user.domain.event.UserDeletedEvent;
import com.lezhin.webtoon.webtoonservice.user.infrastructure.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final ApplicationEventPublisher eventPublisher;

    public User validateUserExists(Long userId) {
        return userJpaRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = validateUserExists(userId);
        userJpaRepository.delete(user);

        eventPublisher.publishEvent(new UserDeletedEvent(this, userId));
    }

}
