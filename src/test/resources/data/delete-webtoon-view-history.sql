INSERT INTO users (id, user_name, gender, type, register_date)
VALUES (1, '홍길동', 'MALE', 'NORMAL', '2023-06-20'),
       (2, '김영희', 'FEMALE', 'ADULT', '2023-06-21');

INSERT INTO webtoon (id, contents_name, author, coin, open_date, type)
VALUES (1, '웹툰1', '작가1', 0, '2023-06-20', 'NORMAL'),
       (2, '웹툰2', '작가2', 0, '2023-06-20', 'NORMAL');

INSERT INTO webtoon_view_history (id, user_id, webtoon_id, view_date)
VALUES (1, 1, 1, '2023-06-22T10:00:00'),
       (2, 1, 2, '2023-06-23T11:00:00'),
       (3, 2, 1, '2023-06-24T12:00:00');

