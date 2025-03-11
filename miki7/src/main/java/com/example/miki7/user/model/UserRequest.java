package com.example.miki7.user.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String username;
    private String password;
    private String nickname;
    private LocalDateTime birthDate;
    private String gender;
    private String profileImage;

}
