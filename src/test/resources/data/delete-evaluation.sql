INSERT INTO users (id, user_name, gender, type, register_date)
VALUES (1, '홍길동', 'MALE', 'NORMAL', '2023-06-20');

INSERT INTO webtoon (id, contents_name, author, coin, open_date, type)
VALUES (1, '웹툰1', '작가1', 0, '2023-06-20', 'NORMAL'),
       (2, '웹툰2', '작가2', 100, '2023-06-20', 'NORMAL');

-- 평가 데이터 삽입
INSERT INTO evaluation (id, user_id, webtoon_id, evaluation_value, comment)
VALUES (1, 1, 1, true, 'Good'),
       (2, 1, 2, false, 'Bad');

