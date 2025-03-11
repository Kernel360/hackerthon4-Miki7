package com.example.miki7.user.db;

import com.example.miki7.review.db.LikeEntity;
import com.example.miki7.review.db.ReviewEntity;
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
    private String userName;

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
}
