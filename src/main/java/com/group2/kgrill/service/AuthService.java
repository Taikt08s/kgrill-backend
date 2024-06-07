package com.group2.kgrill.service;


import com.swd392.group2.kgrill_dto.dto.AuthenticationRequest;
import com.swd392.group2.kgrill_dto.dto.AuthenticationResponse;
import com.swd392.group2.kgrill_dto.dto.GoogleAuthenticationRequest;
import com.swd392.group2.kgrill_dto.dto.RegistrationRequest;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface AuthService {
    void register(RegistrationRequest request) throws MessagingException, UnsupportedEncodingException;

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void activateAccount(String token, HttpServletResponse response) throws MessagingException, UnsupportedEncodingException;

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    AuthenticationResponse findOrCreateUser(GoogleAuthenticationRequest request);
}
