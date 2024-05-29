package com.group2.kgrill.controller;

import com.group2.kgrill.config.LogoutServiceConfig;
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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {
    private final AuthService authService;
    private final LogoutServiceConfig logoutServiceConfig;

    @Operation(
            summary = "Register a new account",
            description = "To register a new account, all information must be filled out completely and cannot be left blank." +
                    " Upon successful registration, a verification email will be sent to the user's Gmail account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Successfully Registered",
                    content = @Content(
                            examples = @ExampleObject(value = """
                                    {
                                      "message": "Successfully Register",
                                      "httpStatus": 202,
                                      "timestamp": "05/29/2024 21:20:36",
                                      "data": "Please check your email for account verification."
                                    }
                                    """))),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class),
                            examples = @ExampleObject(value = """
                                    }
                                    "httpStatus": 400,
                                    "timestamp": "05/29/2024 00:44:00",
                                    "error": "Field 'email' is invalid",
                                    "data": {
                                        "email": "must be a well-formed email address",
                                        "password": "must be at least 8 characters long"
                                    }"""))),
    })
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {
        authService.register(request);
        return CustomSuccessHandler.responseBuilder(HttpStatus.ACCEPTED, "Successfully Register", "Please check your email for account verification.");
    }

    @Operation(
            summary = "Login in to the system",
            description = "Login into the system requires all information to be provided, " +
                    "and validations will be performed. The response will include an access token and a refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Register",
                    content = @Content(
                            examples = @ExampleObject(value = """
                                        {
                                           "httpStatus": 200,
                                           "timestamp": "05/29/2024 11:20:03",
                                           "message": "Successfully SignIn",
                                           "data": {
                                             "accessToken": "xxxx.yyyy.zzzz",
                                             "refreshToken": "xxxx.yyyy.zzzz"
                                        }
                                    """))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(
                            examples = @ExampleObject(value = """
                                        {
                                         "httpStatus": 401,
                                         "timestamp": "05/29/2024 21:24:57",
                                         "message": "Email or Password is incorrect"
                                       }
                                    """))),
            @ApiResponse(responseCode = "451", description = "Account Locked",
                    content = @Content(
                            examples = @ExampleObject(value = """
                                        {
                                         "httpStatus": 401,
                                         "timestamp": "05/29/2024 21:24:57",
                                         "message": "Account is disabled please contact administrator for more information"
                                       }
                                    """))),
    })
    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> signIn(@RequestBody @Valid AuthenticationRequest request) {
        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully SignIn", authService.authenticate(request));
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
    public void accountConfirmation(@RequestParam String token, HttpServletResponse response) throws MessagingException {
        authService.activateAccount(token, response);
    }

    @Operation(
            summary = "Logout of the system",
            description = "Logout of the system, bearer is required",
            tags = {"Authentication"})
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logoutServiceConfig.logout(request, response, authentication);
    }
}
