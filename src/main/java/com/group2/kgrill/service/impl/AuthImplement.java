package com.group2.kgrill.service.impl;

import com.group2.kgrill.config.LogoutServiceConfig;
import com.group2.kgrill.dto.AuthenticationRequest;
import com.group2.kgrill.dto.AuthenticationResponse;
import com.group2.kgrill.dto.RegistrationRequest;
import com.group2.kgrill.enums.EmailTemplateName;
import com.group2.kgrill.enums.TokenType;
import com.group2.kgrill.exception.ActivationTokenException;
import com.group2.kgrill.model.EmailToken;
import com.group2.kgrill.model.Token;
import com.group2.kgrill.model.User;
import com.group2.kgrill.repository.EmailTokenRepository;
import com.group2.kgrill.repository.RoleRepository;
import com.group2.kgrill.repository.TokenRepository;
import com.group2.kgrill.repository.UserRepository;
import com.group2.kgrill.service.AuthService;
import com.group2.kgrill.service.EmailService;
import com.group2.kgrill.service.JwtService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthImplement implements AuthService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailTokenRepository emailTokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;
    @Value("${application.mail.secure.characters}")
    private String emailSecureCharacter;
    private static final Logger logger = LoggerFactory.getLogger(LogoutServiceConfig.class);

    @Override
    public void register(RegistrationRequest request) throws MessagingException {
        var userRole = roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initialized"));
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .address(request.getAddress())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enable(false)
                .role(userRole)
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        emailService.sendEmail(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                (String) newToken,
                "Account activation"
        );

    }

    private Object generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode();
        var token = EmailToken.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .revokedToken(false)
                .user(user)
                .build();
        emailTokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode() {
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            int randomIndex = secureRandom.nextInt(emailSecureCharacter.length());
            codeBuilder.append(emailSecureCharacter.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    private void saveUserToken(User user, String jwtAccessToken, String jwtRefreshToken) {
        var token = Token.builder()
                .user(user)
                .accessToken(jwtAccessToken)
                .refreshToken(jwtRefreshToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());

        claims.put("fullName", user.fullName());
        claims.put("role", user.getRole());

        var jwtAccessToken = jwtService.generateToken(claims, user);
        var jwtRefreshToken = jwtService.generateRefreshToken(user);

        revokeAllUserToken(user);
        saveUserToken(user, jwtAccessToken, jwtRefreshToken);


        return AuthenticationResponse.builder()
                .accessToken(jwtAccessToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }

    private void revokeAllUserToken(User user) {
        var validUserToken = tokenRepository.findAllValidTokensByUser(user.getUserId());
        if (validUserToken.isEmpty())
            return;
        validUserToken.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);
    }

    @Override
    public void activateAccount(String token, HttpServletResponse response) throws MessagingException {

        EmailToken savedToken = emailTokenRepository.findByToken(token)
                .orElseThrow(() -> new ActivationTokenException("Invalid email token"));

        if (savedToken.getValidateAt() != null) {
            throw new ActivationTokenException("Your account is already activated");
        }

        if (savedToken.isRevokedToken()) {
            throw new ActivationTokenException("This activation code is invalid as it has been revoked. Please use the latest activation code sent to your email.");
        }

        if (LocalDateTime.now().isAfter(savedToken.getExpiredAt())) {
            savedToken.setRevokedToken(true);
            emailTokenRepository.save(savedToken);
            sendValidationEmail(savedToken.getUser());
            throw new ActivationTokenException("Activation code has expired. A new code has been sent to your email address");
        }


        var user = userRepository.findById(savedToken.getUser().getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEnable(true);
        userRepository.save(user);
        savedToken.setRevokedToken(true);
        savedToken.setValidateAt(LocalDateTime.now());
        emailTokenRepository.save(savedToken);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("text/plain");
        try {
            response.getWriter().write("Account verification successfully");
        } catch (IOException e) {
            logger.error("Error writing error response", e);
        }

    }
}
