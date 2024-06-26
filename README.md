# Webtoon Service

## 개발 환경
- openJdk21
- SpringBoot 3.3.1
- SpringDataJPA 3.3.1
- querydsl 6.2.1
- H2 DB

## Project Summary
- 의존성의 방향은 도메인 중심
- 양방향 연관관계가 필요한 경우, 이벤트 발행  
- core : 응답 형식 및 에러 처리의 공통 구현
- 테스트 : 필요에 의한 통합, 단위 테스트, 의미있는 값으로 테스트

모든 API는 다음 Swagger UI에서 테스트 해볼 수 있습니다.  
http://localhost:8080/swagger-ui.html

## API List

### 1. 특정 사용자가 해당 작품에 대해 평가('좋아요/싫어요', '댓글')을 할 수 있는 API
> - 작품 당 1개의 평가만 가능하고, 여러 작품에 대해서 평가 가능
> - '좋아요/싫어요'는 필수, 댓글은 선택 (단, 댓글에 특수문자는 불가)

- **API**
  - [EvaluationController](src/main/java/com/lezhin/webtoon/webtoonservice/evaluation/api/EvaluationController.java)
- **TEST**
  - WebMvc Test - [EvaluationControllerTest](src/test/java/com/lezhin/webtoon/webtoonservice/evaluation/api/EvaluationControllerTest.java)
  - 결정 테이블 테스트 - [EvaluationTest.java](src/test/java/com/lezhin/webtoon/webtoonservice/evaluation/domain/EvaluationTest.java)

  | 현재 평가 상태 | 새로운 평가 상태 | 허용 여부 | 결과 |  
      | ------------- | ------------- | -------- | ----- |    
  | 좋아요         | 좋아요         | 불허     | X     |  
  | 좋아요         | 싫어요         | 허용     | O     |  
  | 싫어요         | 좋아요         | 허용     | O     |  
  | 싫어요         | 싫어요         | 불허     | X     |  


### 2. '좋아요'가 가장 많은 작품 3개와 '싫어요'가 가장 많은 작품 3개를 조회하는 API

- **API**
  - [WebtoonController](src/main/java/com/lezhin/webtoon/webtoonservice/webtoon/api/WebtoonController.java)
- **TEST**
  - DataJpaTest - [WebtoonEvaluationQueryDslRepositoryTest](src/test/java/com/lezhin/webtoon/webtoonservice/query/infrastructure/WebtoonEvaluationQueryDslRepositoryTest.java)

### 3. 작품 별로 언제, 어떤 사용자가 조회했는지 이력을 조회하는 API

- **API**
  - [WebtoonViewHistoryController](src/main/java/com/lezhin/webtoon/webtoonservice/history/api/WebtoonViewHistoryController.java)
- **TEST**
  - DataJpaTest - [WebtoonViewHistoryJpaRepositoryTest](src/test/java/com/lezhin/webtoon/webtoonservice/history/infrastructure/WebtoonViewHistoryJpaRepositoryTest.java)

### 4. 최근 1주일간 등록 사용자 중 성인 작품을 3개 이상 조회한 사용자 목록을 조회하는 API

- **API**
  - [AdultWebtoonViewController](src/main/java/com/lezhin/webtoon/webtoonservice/query/api/AdultWebtoonViewController.java)
- **TEST**
  - DataJpaTest - [AdultWebtoonViewQueryDslRepositoryTest](src/test/java/com/lezhin/webtoon/webtoonservice/query/infrastructure/AdultWebtoonViewQueryDslRepositoryTest.java)

### 5. 특정 작품을 유로, 무료로 변경할 수 있는 API
> - 무료 변경 시 금액은 0원
> - 유료 변경 시 금액은 입력 값에 따름 (100원-500원)

- **API**
  - [WebtoonController](src/main/java/com/lezhin/webtoon/webtoonservice/webtoon/api/WebtoonController.java)
- **TEST**
  - 경계값 분석 - [WebtoonTest](src/test/java/com/lezhin/webtoon/webtoonservice/webtoon/domain/WebtoonTest.java)

### 6. 특정 사용자 삭제 시 해당 사용자 정보와 사용자의 평가, 조회 이력 모두 삭제하는 API
사용자 정보 삭제 시 이벤트 발행  
-> 사용자 평가 정보 삭제  
-> 조회 이력 삭제  
- **API**
  - [UserController](src/main/java/com/lezhin/webtoon/webtoonservice/user/api/UserController.java)
- **TEST**
  - DataJpaTest - [EvaluationJpaRepositoryTest](src/test/java/com/lezhin/webtoon/webtoonservice/evaluation/infrastructure/EvaluationJpaRepositoryTest.java)  
  - DataJpaTest - [WebtoonViewHistoryJpaRepositoryTest](src/test/java/com/lezhin/webtoon/webtoonservice/history/infrastructure/WebtoonViewHistoryJpaRepositoryTest.java)
