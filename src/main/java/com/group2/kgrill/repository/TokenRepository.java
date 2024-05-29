package com.group2.kgrill.repository;

import com.group2.kgrill.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("SELECT t from Token t inner join User u on t.user.userId=u.userId " +
            "where u.userId= :userId and (t.expired =false AND t.revoked =false)")
    List<Token> findAllValidTokensByUser(UUID userId);

    Optional<Token> findByAccessToken(String token);

    Optional<Token> findByRefreshToken(String refreshToken);
}
