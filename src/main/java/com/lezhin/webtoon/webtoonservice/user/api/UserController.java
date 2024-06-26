package com.lezhin.webtoon.webtoonservice.user.api;

import com.lezhin.webtoon.webtoonservice.core.dto.CommonResponse;
import com.lezhin.webtoon.webtoonservice.user.application.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "UserController", description = "사용자 관련 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    private final UserService userService;

    @Operation(summary = "사용자 삭제", description = "특정 사용자를 삭제하고, 해당 사용자의 평가 및 조회 이력을 모두 삭제합니다.")
    @DeleteMapping("{userId}")
    public ResponseEntity<CommonResponse<Void>> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(
                new CommonResponse<>(
                        "DELETED",
                        "정상적으로 삭제되었습니다.",
                        null
                ),
                HttpStatus.NO_CONTENT
        );
    }

}
