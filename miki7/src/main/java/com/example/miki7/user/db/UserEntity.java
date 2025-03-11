package com.example.miki7.user.db;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;
    @Column(unique = true)
    private String nickname;
    private LocalDateTime birthDate;
    private String gender;
    private String profileImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_roles",
            joinColumns= @JoinColumn(name = "user_id")
    )
    private Set<String> roles;
    private String status;
}
