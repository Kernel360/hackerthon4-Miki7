package com.example.miki7.user.db;

import com.example.miki7.review.db.LikeEntity;
import com.example.miki7.review.db.ReviewEntity;
import com.example.miki7.user.model.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
    private LocalDateTime birthDate;
    private String gender;
    private String profileImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns= @JoinColumn(name = "user_id"))
    private Set<String> roles;

    private String status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LikeEntity> likes;

    // UserEntity -> UserDto 변환 메서드
    public UserDto toDto() {
        return UserDto.builder()
                .id(this.id)
                .username(this.username)
                .nickname(this.nickname)
                .birthDate(this.birthDate)
                .gender(this.gender)
                .profileImage(this.profileImage)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .deletedAt(this.deletedAt)
                .roles(this.roles)
                .status(this.status)
                .build();
    }
}
