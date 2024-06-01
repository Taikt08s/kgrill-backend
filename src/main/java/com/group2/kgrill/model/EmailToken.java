package com.group2.kgrill.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "email_token")
public class EmailToken {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "verification_token")
    private String token;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Column(name = "validate_at")
    private LocalDateTime validateAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

