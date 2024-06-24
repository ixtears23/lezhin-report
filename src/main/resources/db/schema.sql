CREATE TABLE IF NOT EXISTS users
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name     VARCHAR(255) NOT NULL,
    user_email     VARCHAR(255) NOT NULL,
    gender        VARCHAR(10)  NOT NULL,
    type          VARCHAR(10)  NOT NULL,
    register_date DATE         NOT NULL
);

CREATE TABLE IF NOT EXISTS webtoon
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    contents_name VARCHAR(255) NOT NULL,
    author        VARCHAR(255) NOT NULL,
    coin          INT          NOT NULL,
    open_date     DATE         NOT NULL
);

CREATE TABLE IF NOT EXISTS evaluation
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT NOT NULL,
    webtoon_id      BIGINT NOT NULL,
    evaluation_type VARCHAR(10) NOT NULL,
    comment         VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS webtoon_view_history
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    webtoon_id BIGINT NOT NULL,
    view_date  TIMESTAMP
);

CREATE INDEX idx_user_email ON users(user_email);
CREATE INDEX idx_webtoon_open_date ON webtoon(open_date);
CREATE INDEX idx_evaluation_user_id_and_evaluation_type ON evaluation(user_id, evaluation_type);
CREATE INDEX idx_webtoon_view_history_user_id ON webtoon_view_history(user_id);
