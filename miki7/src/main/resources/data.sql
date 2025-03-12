
-- 서울의 봄 영화가 없으면 추가
INSERT INTO movie (movie_name, movie_en_name, year, genre, run_time, plot, poster, score, status)
SELECT * FROM (SELECT '서울의 봄', 'Seoul Spring', '2023', '드라마, 스릴러', '120', '대한민국의 역사적 사건을 다룬 영화.', 'https://i.namu.wiki/i/0YvZV_7LUH4UKLL0js0NPlUDe8xl2W3mpcG3uEgpgHN2WBWd9cz3xQMNwQ0ynjel40_7GQXAOW64EoZeXUNpnxAxqN1182SLF6LwQZGbULRHg8Rk_ASB-rtHYJZsj9jHAqjEduDDcPLXuIfLhpbEdg.webp', '5', '정상') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM movie WHERE movie_name = '서울의 봄');

-- 파묘 영화가 없으면 추가
INSERT INTO movie (movie_name, movie_en_name, year, genre, run_time, plot, poster, score, status)
SELECT * FROM (SELECT '파묘', 'Exhuma', '2024', '미스터리, 스릴러', '110', '한 무덤에서 벌어지는 기이한 사건을 그린 이야기.', 'https://i.namu.wiki/i/7496PkkdHn3cgnMN2BrACqbYJvQicf3Wm4otGHfcYV5fzSaAT6ZTwhAOcuM88H8wepgcuxDM4IHSA1HGHe5vgTk2dIc08yYPx4nLFFJyO9bQXdpx27eDlnGoQXrFfp1ard-zlsmuTUDBowxxqfqpAw.webp', '4', '정상') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM movie WHERE movie_name = '파묘');

-- 듄: 파트2 영화가 없으면 추가
INSERT INTO movie (movie_name, movie_en_name, year, genre, run_time, plot, poster, score, status)
SELECT * FROM (SELECT '듄: 파트2', 'Dune: Part Two', '2024', 'SF, 액션', '155', '폴 아트레이디스가 복수를 위해 나서는 이야기.', 'https://i.namu.wiki/i/0hJlBmFq9f6bxkpJoSdGdUexgVXfW65Dg0BqhHv_znH6v49RSlvMiL87CjVpJG-ht-cgCuho5tvksSD-zkRBW3ar5eEmhFY3xudBHIbAHCQhOSyrfniPdNjvxd5VMUVSdUjKqDjtN4-n44ikf7Jo1w.webp', '3', '정상') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM movie WHERE movie_name = '듄: 파트2');

CREATE TABLE IF NOT EXISTS user_roles (
                                            user_id BIGINT,
                                            roles VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(id)
    );

-- members 테이블에 관리자 추가
INSERT INTO user (nickname, password) VALUES ('admin', '$2a$10$Kph3NohVtPYCqvVNnW7CN.sEIf56uX9iQLPF9YKCEA15wVMQsyE/2');

-- member_roles 테이블에 관리자 역할 추가
INSERT INTO user_roles (user_id, roles) VALUES (1, 'USER');
INSERT INTO user_roles (user_id, roles) VALUES (1, 'ADMIN');

-- 🔹 리뷰 작성할 유저 (중복 방지)
INSERT INTO user (nickname, password, username)
SELECT * FROM (SELECT 'reviewer1', '$2a$10$Kph3NohVtPYCqvVNnW7CN.sEIf56uX9iQLPF9YKCEA15wVMQsyE/2','dldjdtjr@gmail.com') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM user WHERE nickname = 'reviewer1');

INSERT INTO user_roles (user_id, roles)
SELECT * FROM (SELECT 2, 'USER') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM user_roles WHERE user_id = 2 AND roles = 'USER');

-- 🔹 영화가 없으면 추가 (기존 데이터 유지)
INSERT INTO movie (movie_name, movie_en_name, year, genre, run_time, plot, poster, score, status)
SELECT * FROM (SELECT '서울의 봄', 'Seoul Spring', '2023', '드라마, 스릴러', '120', '대한민국의 역사적 사건을 다룬 영화.', 'https://example.com/poster1.jpg', '5', '정상') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM movie WHERE movie_name = '서울의 봄');

INSERT INTO movie (movie_name, movie_en_name, year, genre, run_time, plot, poster, score, status)
SELECT * FROM (SELECT '파묘', 'Exhuma', '2024', '미스터리, 스릴러', '110', '한 무덤에서 벌어지는 기이한 사건을 그린 이야기.', 'https://example.com/poster2.jpg', '4', '정상') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM movie WHERE movie_name = '파묘');

-- 🔹 배우 추가
INSERT INTO actor (actor_name)
SELECT * FROM (SELECT '이병헌') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM actor WHERE actor_name = '이병헌');

INSERT INTO actor (actor_name)
SELECT * FROM (SELECT '최민식') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM actor WHERE actor_name = '최민식');

-- 🔹 배역 추가 (배우 - 영화 관계 설정)
INSERT INTO cast (cast_name, movie_id, actor_id)
SELECT * FROM (SELECT '김재규', (SELECT id FROM movie WHERE movie_name = '서울의 봄'), (SELECT id FROM actor WHERE actor_name = '이병헌')) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM cast WHERE cast_name = '김재규');

INSERT INTO cast (cast_name, movie_id, actor_id)
SELECT * FROM (SELECT '박정희', (SELECT id FROM movie WHERE movie_name = '서울의 봄'), (SELECT id FROM actor WHERE actor_name = '최민식')) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM cast WHERE cast_name = '박정희');

-- 🔹 영화 리뷰 추가
INSERT INTO review (review_title, review_content, review_rating, review_image, created_at, status, user_id, movie_id)
SELECT * FROM (SELECT '훌륭한 영화', '정말 감동적인 이야기였습니다.', 5, 'https://example.com/review1.jpg', NOW(), 'ACTIVE', (SELECT id FROM user WHERE nickname = 'reviewer1'), (SELECT id FROM movie WHERE movie_name = '서울의 봄')) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM review WHERE review_title = '훌륭한 영화' AND movie_id = (SELECT id FROM movie WHERE movie_name = '서울의 봄'));

-- 🔹 배역 리뷰 추가
INSERT INTO review (review_title, review_content, review_rating, review_image, created_at, status, user_id, cast_id, movie_id)
SELECT * FROM (SELECT '이병헌 연기 대박', '역할을 완벽하게 소화하셨습니다!', 5, 'https://example.com/review2.jpg', NOW(), 'ACTIVE',
                      (SELECT id FROM user WHERE nickname = 'reviewer1'),
                      (SELECT id FROM cast WHERE cast_name = '김재규'),
                      (SELECT id FROM movie WHERE movie_name = '서울의 봄')) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM review WHERE review_title = '이병헌 연기 대박' AND cast_id = (SELECT id FROM cast WHERE cast_name = '김재규'));


