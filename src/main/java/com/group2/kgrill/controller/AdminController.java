package com.group2.kgrill.controller;

import com.swd392.group2.kgrill_service.dto.CustomUserProfile;
import com.swd392.group2.kgrill_service.dto.UserProfileDto;
import com.swd392.group2.kgrill_service.service.DeliveryOrderService;
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
    private final DeliveryOrderService deliveryOrderService;

    @Operation(
            summary = "View user profile by admin",
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
                                                 "email": "elysia1123@gmail.com",
                                                 "address": "Vinhomes Grand Park, 208 Nguyen Xien, Long Thanh My, District 9 , HCMC",
                                                 "latitude": "10.8493",
                                                 "longitude": "106.7723",
                                                 "gender":"null",
                                                 "phone": "0909123456",
                                                 "role": "USER",
                                                 "account_not_locked": true,
                                                 "dob": ""
                                                 }
                                                  ],
                                             "page_no": "0",
                                             "page_size": "10",
                                             "total_elements": "12",
                                             "total_pages": "2",
                                             "last": false,
                                           }
                                         }
                                    """))),
            @ApiResponse(responseCode = "401", description = "You have no permission to access this page"),
    })
    @GetMapping(value = "/management")
    public ResponseEntity<Object> getAccountList(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(name = "email", defaultValue = "", required = false) String email
    ) {
        return userService.getAllUsersByAdmin(pageNo, pageSize, sortBy, sortDir, email);
    }

    @Operation(
            summary = "Update user profile information by admin",
            description = "Update user information in admin panel.",
            tags = {"Admin"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account information update successfully"),
            @ApiResponse(responseCode = "400", description = "Fail to update account information"),
    })
    @PutMapping(value = "/management/account")
    public ResponseEntity<Object> updateUserProfile(@NotNull UUID id,
                                                     @RequestBody @Valid CustomUserProfile customUserProfile) {
        return userService.updateUserProfileByAdmin(id, customUserProfile);
    }

    @Operation(
            summary = "Get number of users by admin",
            description = "Get number of users by admin in order to show in admin panel. Enter role name to get number of users",
            tags = {"Admin"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Number of users retrieve successfully",
                            content = @Content(
                                    examples = @ExampleObject(value = """
                                        {
                                           "http_status": 200,
                                           "time_stamp": "06/02/2024 17:25:41",
                                           "message": "Number of users retrieve successfully",
                                           "data": 9
                                         }
                                    """))),
                    @ApiResponse(responseCode = "400", description = "Fail to retrieve number of users")
            }
    )
    @GetMapping(value = "/management/number-of-users")
    public ResponseEntity<Object> getNumberOfUsers(@NotNull String roleName) {
        return userService.getNumberOfUsersByRoleId(roleName);
    }

    @Operation(
            summary = "Get number of orders by admin",
            description = "Get number of orders by admin by daily, weekly,monthly and yearly in order to show in admin panel.",
            tags = {"Admin"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Number of orders retrieve successfully",
                            content = @Content(
                                    examples = @ExampleObject(value = """
                                        {
                                           "http_status": 200,
                                           "time_stamp": "06/02/2024 17:25:41",
                                           "message": "Number of users retrieve successfully",
                                           "data": {
                                                  "daily_order": 2,
                                                  "weekly_order": 2,
                                                  "monthly_order": 2,
                                                  "yearly_order": 3
                                           }
                                         }
                                    """))),
                    @ApiResponse(responseCode = "400", description = "Fail to retrieve number of orders")
            }
    )
    @GetMapping(value = "/number-of-orders")
    public ResponseEntity<Object> getNumberOfOrders(){
        return deliveryOrderService.getNumberOfOrders();
    }


    @Operation(
            summary = "Get revenue by daily, monthly or yearly",
            description = "Get revenue by period in order to show in admin panel.",
            tags = {"Admin"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Revenue by period retrieve successfully",
                            content = @Content(
                                    examples = @ExampleObject(value = """
                                        {
                                                    "http_status": 200,
                                                    "time_stamp": "07/02/2024 13:36:52",
                                                    "message": "Successfully retrieved revenue by daily",
                                                    "data": {
                                                      "content": [
                                                        {
                                                          "Delivery_order_id": 1,
                                                          "User_name": "VV T",
                                                          "Package_name": [
                                                            "Combo Bò nướng mĩ vị Tailor",
                                                            "Lẩu Tứ Xuyên"
                                                          ],
                                                          "Delivery_order_status": "Delivered ",
                                                          "Delivery_order_date": "2024-07-01T05:49:21.000+00:00",
                                                          "Delivery_shipped_date": "2024-07-01T06:50:39.000+00:00",
                                                          "Shipper_name": "Shipper T",
                                                          "Delivery_order_value": 1400000
                                                        },
                                                      ],
                                                      "page_no": 0,
                                                      "page_size": 10,
                                                      "total_elements": 3,
                                                      "total_pages": 1,
                                                      "last": true,
                                                      "total_revenue": 3300000,
                                                      "completed_order": 2300000,
                                                      "cancelled_order": 1000000
                                                    }
                                                  }
                                    """))),
                    @ApiResponse(responseCode = "400", description = "Fail to retrieve revenue by period")
            }
    )
    @GetMapping(value = "/revenue")
    public ResponseEntity<Object> getRevenueByPeriod(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(name = "period", defaultValue = "monthly", required = false) String period
            ){
        return deliveryOrderService.getRevenueByPeriod(pageNo, pageSize, sortBy, sortDir, period);
    }

}
