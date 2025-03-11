CREATE TABLE IF NOT EXISTS user_roles (
                                            user_id BIGINT,
                                            roles VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(id)
    );

-- members 테이블에 관리자 추가
-- INSERT INTO user (nickname, password) VALUES ('admin', '$2a$10$Kph3NohVtPYCqvVNnW7CN.sEIf56uX9iQLPF9YKCEA15wVMQsyE/2');

-- member_roles 테이블에 관리자 역할 추가
INSERT INTO user_roles (user_id, roles) VALUES (1, 'USER');
INSERT INTO user_roles (user_id, roles) VALUES (1, 'ADMIN');