INSERT INTO users (user_name, user_email, gender, type, register_date)
VALUES ('홍길동', 'hong@example.com', '남자', '일반', '2023-06-20'),
       ('김영희', 'kim@example.com', '여자', '성인', '2023-06-21'),
       ('이철수', 'lee@example.com', '남자', '일반', '2023-06-22');

INSERT INTO webtoon (contents_name, author, coin, open_date)
VALUES ('웹툰A', '작가1', 100, '2023-06-20'),
       ('웹툰B', '작가2', 200, '2023-06-21'),
       ('웹툰C', '작가3', 300, '2023-06-22');

INSERT INTO evaluation (user_id, webtoon_id, evaluation_type, comment)
VALUES (1, 1, '좋아요', '재밌어요!'),
       (1, 2, '싫어요', '별로예요'),
       (2, 2, '좋아요', '흥미로워요'),
       (3, 1, '좋아요', '추천합니다'),
       (2, 3, '좋아요', '정말 좋았어요'),
       (3, 3, '싫어요', '지루해요');

INSERT INTO webtoon_view_history (user_id, webtoon_id, view_date)
VALUES (1, 1, '2023-06-20 10:00:00'),
       (1, 2, '2023-06-20 11:00:00'),
       (2, 2, '2023-06-21 11:00:00'),
       (3, 1, '2023-06-22 12:00:00'),
       (3, 3, '2023-06-22 13:00:00');
