package com.group2.kgrill.controller;

import com.group2.kgrill.dto.UserProfileDto;
import com.group2.kgrill.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
@Tag(name = "Account", description = "Method for account settings required access token to gain access")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "View user profile",
            description = "View current user information after logging into the system. Passwords, tokens, etc., " +
                    "will not be displayed",
            tags = {"Account"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account information retrieve successfully",
                    content = @Content(
                            examples = @ExampleObject(value = """
                                        {
                                           "httpStatus": 200,
                                           "timestamp": "06/02/2024 17:25:41",
                                           "message": "Successfully retrieved user information",
                                           "data": {
                                             "userId": "a9126139-3c11-47ba-8493-cf7e480c3645",
                                             "firstName": "James",
                                             "lastName": "Bond",
                                             "email": "Sniper6969@gmail.com",
                                             "address": "123 Main St, Springfield",
                                             "gender": null,
                                             "phone": "0877643231",
                                             "profilePic": null,
                                             "authProvider": null,
                                             "roleName": "USER"
                                           }
                                         }
                                    """))),
            @ApiResponse(responseCode = "401", description = "No JWT token found in the request header"),
    })
    @GetMapping("/account")
    public ResponseEntity<Object> getCurrentLoginUser(HttpServletRequest request) {
        return userService.getUserInformation(request);
    }

    @Operation(
            summary = "Update user profile information",
            description = "Update current user information after logging into the system.",
            tags = {"Account"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account information update successfully"),
            @ApiResponse(responseCode = "500", description = "No JWT token found in the request header"),
    })
    @PutMapping(value = "/account/update_profile")
    public ResponseEntity<Object> updateLoggedInUser(@NotNull UUID id,
                                                     @RequestBody @Valid UserProfileDto userProfileDto) {

        UserProfileDto updatedUserProfileDto = userService.updateUserInformation(id, userProfileDto);
        if (updatedUserProfileDto != null) {
            return ResponseEntity.ok().body("User profile information updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user profile information");
        }
    }
}
