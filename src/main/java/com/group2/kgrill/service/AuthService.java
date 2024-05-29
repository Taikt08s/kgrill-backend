package com.group2.kgrill.service;

import com.group2.kgrill.dto.AuthenticationRequest;
import com.group2.kgrill.dto.AuthenticationResponse;
import com.group2.kgrill.dto.RegistrationRequest;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    void register(RegistrationRequest request) throws MessagingException;

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void activateAccount(String token, HttpServletResponse response) throws MessagingException;
}
