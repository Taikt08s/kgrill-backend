package com.group2.kgrill.model;

import com.group2.kgrill.enums.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "access_token",length = 350)
    private String accessToken;

    @Column(name = "refresh_token",length = 350)
    private String refreshToken;

    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean expired;

    private boolean revoked;

}
