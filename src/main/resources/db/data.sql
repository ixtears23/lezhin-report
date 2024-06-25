INSERT INTO users (user_name, user_email, gender, type, register_date)
VALUES ('홍길동', 'hong@example.com', 'MALE', 'ADULT', '2023-06-20'),
       ('김영희', 'kim@example.com', 'FEMALE', 'NORMAL', '2023-06-21'),
       ('이철수', 'lee@example.com', 'MALE', 'ADULT', '2023-06-22');

INSERT INTO webtoon (contents_name, author, coin, open_date, type)
VALUES ('웹툰A', '작가1', 0, '2023-06-20', 'NORMAL'),
       ('웹툰B', '작가2', 200, '2023-06-21', 'ADULT'),
       ('웹툰C', '작가3', 200, '2023-06-21', 'ADULT'),
       ('웹툰D', '작가4', 300, '2023-06-22', 'ADULT');

INSERT INTO evaluation (user_id, webtoon_id, evaluation_value, comment)
VALUES (1, 1, 1, '재밌어요!'),
       (1, 2, 0, '별로예요'),
       (2, 2, 1, '흥미로워요'),
       (3, 1, 1, '추천합니다'),
       (2, 3, 1, '정말 좋았어요'),
       (3, 3, 0, '지루해요');

INSERT INTO webtoon_view_history (user_id, webtoon_id, view_date)
VALUES (1, 1, DATEADD('DAY', -1, NOW())),
       (1, 2, DATEADD('DAY', -1, NOW())),
       (1, 3, DATEADD('DAY', -1, NOW())),
       (1, 4, DATEADD('DAY', -1, NOW())),
       (2, 1, DATEADD('DAY', -2, NOW())),
       (2, 1, DATEADD('DAY', -2, NOW())),
       (2, 1, DATEADD('DAY', -2, NOW())),
       (3, 1, DATEADD('DAY', -5, NOW())),
       (3, 2, DATEADD('DAY', -5, NOW())),
       (3, 3, DATEADD('DAY', -5, NOW())),
       (3, 4, DATEADD('DAY', -6, NOW()));