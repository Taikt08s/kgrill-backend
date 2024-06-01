package com.group2.kgrill.controller;

import com.group2.kgrill.service.AuthService;
import com.group2.kgrill.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
@Tag(name = "Account",description = "Method for account settings required access token to gain access")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "View user profile",
            description = "View current user information after logging into the system. Passwords, tokens, etc., " +
                    "will not be displayed",
            tags = {"Account"})
    @GetMapping("/account")
    public ResponseEntity<Object> getCurrentLoginUser(HttpServletRequest request) {
        return userService.getUserInformation(request);
    }
}
