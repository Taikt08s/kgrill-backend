package com.group2.kgrill.controller;

import com.group2.kgrill.dto.AuthenticationRequest;
import com.group2.kgrill.dto.RegistrationRequest;
import com.group2.kgrill.exception.CustomSuccessHandler;
import com.group2.kgrill.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "Register a new account",
            description = "To register a new account, all information must be filled out and cannot be left blank. " +
                    "Upon successful registration, the response will include an access token and a refresh token")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {
        authService.register(request);
        return CustomSuccessHandler.responseBuilder("Successfully Register", HttpStatus.ACCEPTED, "Please check your email for account verification.");
    }

    @Operation(
            summary = "Login in to the system",
            description = "Login into the system requires all information to be provided, " +
                    "and validations will be performed. The response will include an access token and a refresh token")
    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> signIn(@RequestBody @Valid AuthenticationRequest request) {
        return CustomSuccessHandler.responseBuilder("Successfully SignIn", HttpStatus.OK, authService.authenticate(request));
    }

    @Operation(
            summary = "Enter confirmation code",
            description = "After registering successfully, the user will need to enter the 6-digit confirmation code sent " +
                    "to their email to activate the account.")
    @PostMapping("/activate-account")
    public void accountConfirmation(@RequestParam String token) throws MessagingException {
        authService.activateAccount(token);
    }
}
