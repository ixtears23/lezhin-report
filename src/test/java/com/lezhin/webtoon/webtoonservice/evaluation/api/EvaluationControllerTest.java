package com.lezhin.webtoon.webtoonservice.evaluation.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lezhin.webtoon.webtoonservice.core.exception.GlobalExceptionHandler;
import com.lezhin.webtoon.webtoonservice.evaluation.api.dto.CreateEvaluation;
import com.lezhin.webtoon.webtoonservice.evaluation.domain.EvaluationErrorCode;
import com.lezhin.webtoon.webtoonservice.evaluation.domain.EvaluationException;
import com.lezhin.webtoon.webtoonservice.evaluation.application.EvaluationService;
import com.lezhin.webtoon.webtoonservice.evaluation.domain.Evaluation;
import com.lezhin.webtoon.webtoonservice.user.application.UserErrorCode;
import com.lezhin.webtoon.webtoonservice.user.application.UserException;
import com.lezhin.webtoon.webtoonservice.user.domain.User;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.exception.WebtoonErrorCode;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.exception.WebtoonException;
import com.lezhin.webtoon.webtoonservice.webtoon.domain.Webtoon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {EvaluationController.class, GlobalExceptionHandler.class})
class EvaluationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EvaluationService evaluationService;
    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("성공적인 평가 생성")
    @Test
    void createEvaluationSuccess() throws Exception {
        final long userId = 1L;
        final long webtoonId = 1L;
        final boolean evaluationValue = true;
        final String comment = "재밌어요";

        CreateEvaluation.Request request = createEvaluationRequest(userId, webtoonId, evaluationValue, comment);

        when(evaluationService.createEvaluation(ArgumentMatchers.any(CreateEvaluation.Request.class)))
                .thenReturn(Evaluation.builder()
                        .user(User.builder()
                                .id(userId)
                                .build())
                        .webtoon(Webtoon.builder()
                                .id(webtoonId)
                                .build())
                        .evaluationValue(evaluationValue)
                        .comment(comment)
                        .build()
                );

        mockMvc.perform(post("/api/v1/evaluations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("CREATED"))
                .andExpect(jsonPath("$.message").value("정상적으로 평가되었습니다."));
    }

    @DisplayName("사용자 ID 누락으로 인한 평가 생성 실패")
    @Test
    void createEvaluationFailureMissingUserId() throws Exception {
        final long webtoonId = 1L;
        final boolean evaluationValue = true;
        final String comment = "재밌어요";

        CreateEvaluation.Request request = CreateEvaluation.Request.builder()
                .webtoonId(webtoonId)
                .evaluationValue(evaluationValue)
                .comment(comment)
                .build();

        mockMvc.perform(post("/api/v1/evaluations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorFiled").value( "userId"))
                .andExpect(jsonPath("$.message").value("사용자 ID는 필수 항목입니다."));
    }


    @DisplayName("사용자 ID 누락 및 특수문자가 포함된 댓글로 인한 평가 생성 실패")
    @Test
    void createEvaluationFailureMissingUserIdAndInvalidComment() throws Exception {
        final long webtoonId = 1L;
        final boolean evaluationValue = true;
        final String comment = "재밌어요!";

        CreateEvaluation.Request request = CreateEvaluation.Request.builder()
                .webtoonId(webtoonId)
                .evaluationValue(evaluationValue)
                .comment(comment)
                .build();

        mockMvc.perform(post("/api/v1/evaluations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[?(@.errorFiled == 'comment')].message").value("댓글에 특수문자는 포함될 수 없습니다."))
                .andExpect(jsonPath("$[?(@.errorFiled == 'userId')].message").value("사용자 ID는 필수 항목입니다."));
    }

    @DisplayName("특수문자가 포함된 댓글로 인한 평가 생성 실패")
    @Test
    void createEvaluationFailureInvalidComment() throws Exception {
        final long userId = 1L;
        final long webtoonId = 1L;
        final boolean evaluationValue = true;
        final String comment = "재밌어요!";

        CreateEvaluation.Request request = createEvaluationRequest(userId, webtoonId, evaluationValue, comment);

        mockMvc.perform(post("/api/v1/evaluations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorFiled").value("comment"))
                .andExpect(jsonPath("$.message").value("댓글에 특수문자는 포함될 수 없습니다."));
    }

    @DisplayName("존재하지 않는 사용자 ID로 인한 평가 생성 실패")
    @Test
    void createEvaluationFailureNonExistentUser() throws Exception {
        final long userId = 99L;
        final long webtoonId = 1L;
        final boolean evaluationValue = true;
        final String comment = "재밌어요";

        CreateEvaluation.Request request = createEvaluationRequest(userId, webtoonId, evaluationValue, comment);

        when(evaluationService.createEvaluation(ArgumentMatchers.any(CreateEvaluation.Request.class)))
                .thenThrow(new UserException(UserErrorCode.USER_NOT_FOUND));

        mockMvc.perform(post("/api/v1/evaluations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(UserErrorCode.USER_NOT_FOUND.name()))
                .andExpect(jsonPath("$.message").value(UserErrorCode.USER_NOT_FOUND.getErrorMessage()));
    }

    @DisplayName("존재하지 않는 웹툰 ID로 인한 평가 생성 실패")
    @Test
    void createEvaluationFailureNonExistentWebtoon() throws Exception {
        final long userId = 99L;
        final long webtoonId = 1L;
        final boolean evaluationValue = true;
        final String comment = "재밌어요";

        CreateEvaluation.Request request = createEvaluationRequest(userId, webtoonId, evaluationValue, comment);

        when(evaluationService.createEvaluation(ArgumentMatchers.any(CreateEvaluation.Request.class)))
                .thenThrow(new WebtoonException(WebtoonErrorCode.WEBTOON_NOT_FOUND));

        mockMvc.perform(post("/api/v1/evaluations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(WebtoonErrorCode.WEBTOON_NOT_FOUND.name()))
                .andExpect(jsonPath("$.message").value(WebtoonErrorCode.WEBTOON_NOT_FOUND.getErrorMessage()));
    }

    private static CreateEvaluation.Request createEvaluationRequest(long userId,
                                                                    long webtoonId,
                                                                    boolean evaluationValue,
                                                                    String comment) {
        return CreateEvaluation.Request.builder()
                .userId(userId)
                .webtoonId(webtoonId)
                .evaluationValue(evaluationValue)
                .comment(comment)
                .build();
    }

    @DisplayName("중복된 평가로 인한 평가 생성 실패")
    @Test
    void createEvaluationFailureDuplicateEvaluation() throws Exception {
        final long userId = 1L;
        final long webtoonId = 1L;
        final boolean evaluationValue = true;
        final String comment = "재밌어요";

        CreateEvaluation.Request request = createEvaluationRequest(userId, webtoonId, evaluationValue, comment);

        when(evaluationService.createEvaluation(ArgumentMatchers.any(CreateEvaluation.Request.class)))
                .thenThrow(new EvaluationException(EvaluationErrorCode.ALREADY_EVALUATED));

        mockMvc.perform(post("/api/v1/evaluations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(EvaluationErrorCode.ALREADY_EVALUATED.name()))
                .andExpect(jsonPath("$.message").value(EvaluationErrorCode.ALREADY_EVALUATED.getErrorMessage()));
    }
}