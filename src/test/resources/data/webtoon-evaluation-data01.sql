-- 사용자 데이터 삽입
INSERT INTO users (id, user_name, gender, type, register_date)
VALUES (1, 'User1', 'MALE', 'NORMAL', '2023-01-01'),
       (2, 'User2', 'FEMALE', 'NORMAL', '2023-01-01');

-- 웹툰 데이터 삽입
INSERT INTO webtoon (id, contents_name, author, coin, open_date)
VALUES (1, 'Webtoon1', 'Author1', 100, '2023-01-01'),
       (2, 'Webtoon2', 'Author2', 200, '2023-01-01'),
       (3, 'Webtoon3', 'Author3', 300, '2023-01-01');

-- 평가 데이터 삽입
INSERT INTO evaluation (id, user_id, webtoon_id, evaluation_value, comment)
VALUES (1, 1, 1, true, 'Good'),
       (2, 2, 1, false, 'Bad'),
       (3, 1, 2, true, 'Excellent'),
       (4, 2, 3, true, 'Awesome');
