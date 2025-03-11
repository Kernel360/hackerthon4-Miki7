-- 서울의 봄 영화가 없으면 추가
INSERT INTO movie (movie_name, movie_en_name, year, genre, run_time, plot, poster, score, status)
SELECT * FROM (SELECT '서울의 봄', 'Seoul Spring', '2023', '드라마, 스릴러', '120', '대한민국의 역사적 사건을 다룬 영화.', 'https://example.com/poster1.jpg', '5', '정상') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM movie WHERE movie_name = '서울의 봄');

-- 파묘 영화가 없으면 추가
INSERT INTO movie (movie_name, movie_en_name, year, genre, run_time, plot, poster, score, status)
SELECT * FROM (SELECT '파묘', 'Exhuma', '2024', '미스터리, 스릴러', '110', '한 무덤에서 벌어지는 기이한 사건을 그린 이야기.', 'https://example.com/poster2.jpg', '4', '정상') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM movie WHERE movie_name = '파묘');

-- 듄: 파트2 영화가 없으면 추가
INSERT INTO movie (movie_name, movie_en_name, year, genre, run_time, plot, poster, score, status)
SELECT * FROM (SELECT '듄: 파트2', 'Dune: Part Two', '2024', 'SF, 액션', '155', '폴 아트레이디스가 복수를 위해 나서는 이야기.', 'https://example.com/poster3.jpg', '3', '정상') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM movie WHERE movie_name = '듄: 파트2');
