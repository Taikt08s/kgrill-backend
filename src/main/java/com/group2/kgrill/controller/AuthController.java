package com.group2.kgrill.controller;

import com.group2.kgrill.dto.AuthenticationRequest;
import com.group2.kgrill.dto.AuthenticationResponse;
import com.group2.kgrill.dto.RegistrationRequest;
import com.group2.kgrill.exception.CustomSuccessHandler;
import com.group2.kgrill.exception.ExceptionResponse;
import com.group2.kgrill.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
            description = "To register a new account, all information must be filled out completely and cannot be left blank." +
                    " Upon successful registration, a verification email will be sent to the user's Gmail account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Successfully Registered"),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "httpStatus": 400,
                                        "timestamp": "05/29/2024 00:44:00",
                                        "error": "Field 'email' is invalid",
                                        "data": {
                                            "email": "must be a well-formed email address",
                                            "password": "must be at least 8 characters long"
                                        }
                                    }"""))),
    })
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {
        authService.register(request);
        return CustomSuccessHandler.responseBuilder("Successfully Register", HttpStatus.ACCEPTED, "Please check your email for account verification.");
    }

    @Operation(
            summary = "Login in to the system",
            description = "Login into the system requires all information to be provided, " +
                    "and validations will be performed. The response will include an access token and a refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully SignIn",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        {
                                           "message": "Successfully SignIn",
                                           "httpStatus": 200,
                                           "timestamp": "05/29/2024 11:20:03",
                                           "data": {
                                             "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjp7InJvbGVJZCI6MSwicm9sZU5hbWUiOiJVU0VSIiwiY3JlYXRlZERhdGUiOjE3MTY5MTQ5MTEwMDAsImxhc3RNb2RpZmllZERhdGUiOjE3MTY5MTQ5MTIwMDB9LCJmdWxsTmFtZSI6IkRhbmcgRGluaCBUYWkiLCJzdWIiOiJzdHllbWF0aWNAZ21haWwuY29tIiwiaWF0IjoxNzE2OTU2NDAzLCJleHAiOjE3MTY5NTgyMDMsImF1dGhvcml0aWVzIjpbIlVTRVIiXX0.kbA1vVt5AyocVTX1YCv_oBVuuPdiiiKYEVd-9NzZiPyNS48YTOGGjdIzTotQUkv3wEzGWACjtxKx1tSWOIOHKA",
                                             "refreshToken": null
                                           }
                                         }
                                    }"""))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> signIn(@RequestBody @Valid AuthenticationRequest request) {
        return CustomSuccessHandler.responseBuilder("Successfully SignIn", HttpStatus.OK, authService.authenticate(request));
    }

    @Operation(
            summary = "Enter confirmation code",
            description = "After registering successfully, the user will need to enter the 6-digit confirmation code sent " +
                    "to their email to activate the account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email Verification Successfully"),
            @ApiResponse(responseCode = "400", description = "Email Verification Failed")
    })
    @PostMapping("/activate-account")
    @ResponseStatus(HttpStatus.OK)
    public void accountConfirmation(@RequestParam String token) throws MessagingException {
        authService.activateAccount(token);
    }
}
