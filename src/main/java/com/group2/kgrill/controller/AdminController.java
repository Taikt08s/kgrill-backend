package com.group2.kgrill.controller;

import com.swd392.group2.kgrill_service.dto.CustomUserProfile;
import com.swd392.group2.kgrill_service.dto.UserProfileDto;
import com.swd392.group2.kgrill_service.service.UserService;
import com.swd392.group2.kgrill_service.util.AppConstants;
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
@RequestMapping("admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Controller responsible for handling admin operations")
public class AdminController {

    private final UserService userService;

    @Operation(
            summary = "View user profile",
            description = "View all user information in admin panel. Passwords, tokens, etc., " +
                    "will not be displayed",
            tags = {"Admin"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts information retrieve successfully",
                    content = @Content(
                            examples = @ExampleObject(value = """
                                        {
                                           "http_status": 200,
                                           "time_stamp": "06/02/2024 17:25:41",
                                           "message": "Successfully retrieved user list information",
                                           "data": {
                                             "content": [
                                               {
                                                 "user_id": "a9126139-3c11-47ba-8493-cf7e480c3645",
                                                 "first_name": "Vo Van",
                                                 "last_name": "Tinh",
                                                 "email": "tinhvv02012003@gmail.com",
                                                 "address": "Vinhomes Grand Park, 208 Nguyen Xien, Long Thanh My, District 9 , HCMC",
                                                 "account_not_locked": true
                                                 }
                                                  ],
                                             "page_no": "0",
                                             "page_size": "1",
                                             "total_elements": "10",
                                             "total_pages": "1",
                                             "last": false,
                                           }
                                         }
                                    """))),
            @ApiResponse(responseCode = "401", description = "You have no permission to access this page"),
    })
    @GetMapping(value = "/accounts")
    public ResponseEntity<Object> getAccountList(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(name = "email", defaultValue = "", required = false) String email
    ) {
        return ResponseEntity.ok(userService.getAllUsers(pageNo, pageSize, sortBy, sortDir, email));
    }

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
                                           "http_status": 200,
                                           "time_stamp": "06/02/2024 17:25:41",
                                           "message": "Successfully retrieved user information",
                                           "data": {
                                             "userId": "a9126139-3c11-47ba-8493-cf7e480c3645",
                                             "first_name": "James",
                                             "last_name": "Bond",
                                             "email": "Sniper6969@gmail.com",
                                             "address": "123 Main St, Springfield",
                                             "account_not_locked": "true"
                                           }
                                         }
                                    """))),
            @ApiResponse(responseCode = "401", description = "No JWT token found in the request header"),
    })
    @GetMapping("/profile")
    public ResponseEntity<Object> getCurrentLoginUser(HttpServletRequest request) {
        return userService.getUserInformation(request);
    }

    @Operation(
            summary = "Update user profile information",
            description = "Update user information in admin panel.",
            tags = {"Admin"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account information update successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    @PutMapping(value = "/profile")
    public ResponseEntity<Object> updateUserProfile(@NotNull UUID id,
                                                     @RequestBody @Valid CustomUserProfile customUserProfile) {
        return userService.updateUserProfileByAdmin(id, customUserProfile);
    }


}
