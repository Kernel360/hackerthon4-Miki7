package com.example.miki7;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void encodePassword() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123"; // 암호화할 원본 비밀번호
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("암호화된 비밀번호: " + encodedPassword);
        // 출력된 암호화 문자열을 복사하여 SQL 파일에 붙여넣기
    }
}
