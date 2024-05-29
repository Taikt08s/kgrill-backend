package com.group2.kgrill.service.impl;

import com.group2.kgrill.dto.AuthenticationRequest;
import com.group2.kgrill.dto.AuthenticationResponse;
import com.group2.kgrill.dto.RegistrationRequest;
import com.group2.kgrill.enums.EmailTemplateName;
import com.group2.kgrill.exception.ActivationTokenException;
import com.group2.kgrill.model.EmailToken;
import com.group2.kgrill.model.User;
import com.group2.kgrill.repository.EmailTokenRepository;
import com.group2.kgrill.repository.RoleRepository;
import com.group2.kgrill.repository.UserRepository;
import com.group2.kgrill.service.AuthService;
import com.group2.kgrill.service.EmailService;
import com.group2.kgrill.service.JwtService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;
    @Value("${application.mail.secure.characters}")
    private String emailSecureCharacter;

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
                .expiredAt(LocalDateTime.now().plusMinutes(2))
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
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    @Override
    public void activateAccount(String token) throws MessagingException {
        EmailToken savedToken = emailTokenRepository.findByToken(token)
                .orElseThrow(() -> new ActivationTokenException("Invalid email token"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiredAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new ActivationTokenException("Activation code has expired. A new code have been send to your email address");
        }
        var user = userRepository.findById(savedToken.getUser().getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEnable(true);
        userRepository.save(user);
        savedToken.setValidateAt(LocalDateTime.now());
        emailTokenRepository.save(savedToken);

    }
}
