CREATE TABLE IF NOT EXISTS users
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 ID, 자동 증가',
    user_name     VARCHAR(255) NOT NULL COMMENT '사용자 이름',
    user_email    VARCHAR(255) NOT NULL COMMENT '사용자 이메일',
    gender        ENUM('MALE', 'FEMALE')  NOT NULL COMMENT '성별(MALE: 남자, FEMALE: 여자)',
    type          ENUM('NORMAL', 'ADULT')  NOT NULL COMMENT '유형(NORMAL: 일반, ADULT: 성인)',
    register_date DATE         NOT NULL COMMENT '서비스 등록일'
);

-- 작품 테이블
CREATE TABLE IF NOT EXISTS webtoon
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '작품 ID, 자동 증가',
    contents_name VARCHAR(255) NOT NULL COMMENT '작품명',
    author        VARCHAR(255) NOT NULL COMMENT '작가',
    coin          INT          NOT NULL COMMENT '금액',
    open_date     DATE         NOT NULL COMMENT '서비스 제공일'
);

-- 평가 테이블
CREATE TABLE IF NOT EXISTS evaluation
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '평가 ID, 자동 증가',
    user_id          BIGINT COMMENT '사용자 ID',
    webtoon_id       BIGINT COMMENT '웹툰 ID',
    evaluation_value TINYINT NOT NULL COMMENT '평가 값 (1: 좋아요, 0: 싫어요)',
    comment          VARCHAR(255) COMMENT '댓글'
);

-- 웹툰 조회 이력 테이블
CREATE TABLE IF NOT EXISTS webtoon_view_history
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '조회 이력 ID, 자동 증가',
    user_id    BIGINT COMMENT '사용자 ID',
    webtoon_id BIGINT COMMENT '웹툰 ID',
    view_date  TIMESTAMP COMMENT '조회 일시'
);


CREATE INDEX idx_user_email ON users(user_email);
CREATE INDEX idx_webtoon_open_date ON webtoon(open_date);
CREATE INDEX idx_evaluation_user_id_and_evaluation_type ON evaluation(user_id, evaluation_value);
CREATE INDEX idx_webtoon_view_history_user_id ON webtoon_view_history(user_id);
