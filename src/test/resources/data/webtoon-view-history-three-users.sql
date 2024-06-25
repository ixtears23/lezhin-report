INSERT INTO users (id, user_name, gender, type, register_date) VALUES (1, '홍길동', 'MALE', 'ADULT', '2023-06-20');
INSERT INTO users (id, user_name, gender, type, register_date) VALUES (2, '이철수', 'MALE', 'NORMAL', '2023-06-21');
INSERT INTO users (id, user_name, gender, type, register_date) VALUES (3, '박영희', 'FEMALE', 'NORMAL', '2023-06-22');

INSERT INTO webtoon (id, contents_name, author, coin, open_date, type) VALUES (1, '성인 웹툰1', '작가1', 0, '2023-06-20', 'ADULT');
INSERT INTO webtoon (id, contents_name, author, coin, open_date, type) VALUES (2, '성인 웹툰2', '작가2', 0, '2023-06-20', 'ADULT');
INSERT INTO webtoon (id, contents_name, author, coin, open_date, type) VALUES (3, '성인 웹툰3', '작가3', 0, '2023-06-20', 'ADULT');
INSERT INTO webtoon (id, contents_name, author, coin, open_date, type) VALUES (4, '성인 웹툰4', '작가4', 0, '2023-06-20', 'ADULT');

INSERT INTO webtoon_view_history (id, user_id, webtoon_id, view_date) VALUES (1, 1, 1, DATEADD('DAY', -1, NOW()));
INSERT INTO webtoon_view_history (id, user_id, webtoon_id, view_date) VALUES (2, 1, 2, DATEADD('DAY', -1, NOW()));
INSERT INTO webtoon_view_history (id, user_id, webtoon_id, view_date) VALUES (3, 1, 3, DATEADD('DAY', -1, NOW()));

INSERT INTO webtoon_view_history (id, user_id, webtoon_id, view_date) VALUES (4, 2, 1, DATEADD('DAY', -1, NOW()));
INSERT INTO webtoon_view_history (id, user_id, webtoon_id, view_date) VALUES (5, 2, 2, DATEADD('DAY', -1, NOW()));
INSERT INTO webtoon_view_history (id, user_id, webtoon_id, view_date) VALUES (6, 2, 3, DATEADD('DAY', -1, NOW()));

INSERT INTO webtoon_view_history (id, user_id, webtoon_id, view_date) VALUES (7, 3, 1, DATEADD('DAY', -1, NOW()));
INSERT INTO webtoon_view_history (id, user_id, webtoon_id, view_date) VALUES (8, 3, 2, DATEADD('DAY', -1, NOW()));
INSERT INTO webtoon_view_history (id, user_id, webtoon_id, view_date) VALUES (9, 3, 3, DATEADD('DAY', -1, NOW()));
INSERT INTO webtoon_view_history (id, user_id, webtoon_id, view_date) VALUES (10, 3, 4, DATEADD('DAY', -1, NOW()));
