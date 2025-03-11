package com.example.miki7.user.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String password;
    private String nickname;
    private LocalDateTime birthDate;
    private String gender;
    private String profileImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private String status;

}
