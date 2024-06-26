package com.lezhin.webtoon.webtoonservice.query.api;

import com.lezhin.webtoon.webtoonservice.core.dto.CommonResponse;
import com.lezhin.webtoon.webtoonservice.query.application.AdultWebtoonViewService;
import com.lezhin.webtoon.webtoonservice.query.domain.AdultWebtoonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "AdultWebtoonViewController", description = "성인 작품 조회 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/webtoons")
@RestController
public class AdultWebtoonViewController {
    private final AdultWebtoonViewService adultWebtoonViewService;

    @Operation(summary = "최근 1주일간 성인 작품을 3개 이상 조회한 사용자 목록 조회", description = "최근 1주일간 등록된 사용자 중 성인 작품을 3개 이상 조회한 사용자를 조회합니다.")
    @GetMapping("/adult-webtoon-viewers")
    public ResponseEntity<CommonResponse<List<AdultWebtoonView>>> getAdultWebtoonViewers() {
        List<AdultWebtoonView> adultWebtoonView = adultWebtoonViewService.getUsersWithThreeOrMoreAdultWebtoonViews();
        return new ResponseEntity<>(
                new CommonResponse<>(
                        "SUCCESS",
                        "최근 1주일간 성인 웹툰을 3개 이상 조회한 사용자 목록 조회에 성공했습니다.",
                        adultWebtoonView
                ),
                HttpStatus.OK
        );
    }
}
