-- 사용자 데이터 삽입
INSERT INTO users (id, user_name, gender, type, register_date)
VALUES (1, 'User1', 'MALE', 'NORMAL', '2023-01-01'),
       (2, 'User2', 'FEMALE', 'NORMAL', '2023-01-01'),
       (3, 'User3', 'MALE', 'NORMAL', '2023-01-01'),
       (4, 'User4', 'MALE', 'NORMAL', '2023-01-01');

-- 웹툰 데이터 삽입
INSERT INTO webtoon (id, contents_name, author, coin, open_date)
VALUES (1, 'Webtoon1', 'Author1', 100, '2023-01-01'),
       (2, 'Webtoon2', 'Author2', 200, '2023-01-01'),
       (3, 'Webtoon3', 'Author3', 300, '2023-01-01'),
       (4, 'Webtoon4', 'Author4', 400, '2023-01-01');

-- 평가 데이터 삽입
INSERT INTO evaluation (id, user_id, webtoon_id, evaluation_value, comment)
VALUES (1, 1, 1, false, 'bad'),
       (2, 2, 1, false, 'bad'),
       (3, 3, 2, false, 'bad'),
       (4, 4, 2, false, 'bad'),
       (5, 1, 3, false, 'bad'),
       (6, 2, 3, false, 'bad'),
       (7, 3, 4, false, 'bad'),
       (8, 4, 4, false, 'bad');
