package com.lezhin.webtoon.webtoonservice.history.api;

import com.lezhin.webtoon.webtoonservice.core.dto.CommonResponse;
import com.lezhin.webtoon.webtoonservice.history.application.WebtoonViewHistoryService;
import com.lezhin.webtoon.webtoonservice.history.domain.WebtoonViewHistory;
import com.lezhin.webtoon.webtoonservice.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "WebtoonViewHistoryController", description = "조회 이력 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/webtoons")
@RestController
public class WebtoonViewHistoryController {
    private final WebtoonViewHistoryService webtoonViewHistoryService;

    @Operation(summary = "작품 별로 언제, 어떤 사용자가 조회했는지 이력을 조회하는 API", description = "특정 작품에 대해 언제, 어떤 사용자가 조회했는지 이력을 조회합니다.")
    @GetMapping("/{webtoonId}/view-history")
    public ResponseEntity<CommonResponse<List<WebtoonViewHistory>>> getViewHistory(@PathVariable Long webtoonId) {
        List<WebtoonViewHistory> viewHistory = webtoonViewHistoryService.getViewHistoryByWebtoonId(webtoonId);
        return new ResponseEntity<>(
                new CommonResponse<>(
                        "SUCCESS",
                        "조회 이력을 성공적으로 가져왔습니다.",
                        viewHistory
                ),
                HttpStatus.OK
        );
    }

}
