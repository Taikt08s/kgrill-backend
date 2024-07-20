package com.group2.kgrill.controller;

import com.swd392.group2.kgrill_service.dto.CustomUserProfile;
import com.swd392.group2.kgrill_service.service.DeliveryOrderService;
import com.swd392.group2.kgrill_service.service.ShipperService;
import com.swd392.group2.kgrill_service.service.UserService;
import com.swd392.group2.kgrill_service.util.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Controller responsible for handling admin operations")
public class AdminController {

    private final DeliveryOrderService deliveryOrderService;
    private final ShipperService shipperService;
    private final UserService userService;

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
    public ResponseEntity<Object> accountList(
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
    public ResponseEntity<Object> userProfile(@NotNull UUID id,
                                                    @RequestBody @Valid CustomUserProfile customUserProfile) {
        return userService.updateUserProfileByAdmin(id, customUserProfile);
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
                                                   "message": "Number of orders retrieve successfully",
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
    public ResponseEntity<Object> NumberOfOrders() {
        return deliveryOrderService.getNumberOfOrders();
    }


    @Operation(
            summary = "Get revenue by daily, monthly or yearly",
            description = "Get revenue by period and LocalDate in order to show in admin panel.\n" +
                    "Default sorted by orderDate. SortBy value= totalRevenue, completedOrder or cancelledOrder",
            tags = {"Admin"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Revenue by period retrieve successfully",
                            content = @Content(
                                    examples = @ExampleObject(value = """
                                             {
                                                       "http_status": 200,
                                                       "time_stamp": "07/06/2024 16:16:00",
                                                       "message": "Successfully retrieved revenue",
                                                       "data": {
                                                         "content": [
                                                           {
                                                             "order_date": "2024-06",
                                                             "total_order_number": 1,
                                                             "completed_number": 1,
                                                             "cancelled_number": 0,
                                                             "total_revenue": 300000,
                                                             "completed_order": 300000,
                                                             "cancelled_order": 0
                                                           }
                                                         ],
                                                         "page_no": 1,
                                                         "page_size": 1,
                                                         "total_elements": 4,
                                                         "total_pages": 4,
                                                         "last": false
                                                       }
                                                     }
                                            """))),
                    @ApiResponse(responseCode = "400", description = "Fail to retrieve revenue by period")
            }
    )
    @GetMapping(value = "/revenue")
    public ResponseEntity<Object> revenue(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "orderDate", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(name = "period", defaultValue = "daily", required = false) String period,
            @RequestParam(name = "date", defaultValue = "", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        if (date == null) {
            date = LocalDate.now(); // Sử dụng ngày hiện tại nếu không có giá trị truyền vào
        }
        return deliveryOrderService.getRevenueByPeriod(pageNo, pageSize, sortBy, sortDir, period, date.plusDays(1));
    }

    @Operation(
            summary = "Get revenue by daily, monthly or yearly",
            description = "Get revenue detail by period and LocalDate in order to show in admin panel. \n" +
                    "Default sorted by orderDate. SortBy value= shippedDate, status, orderValue,...",
            tags = {"Admin"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Revenue by period retrieve successfully",
                            content = @Content(
                                    examples = @ExampleObject(value = """
                                             {
                                                                "http_status": 200,
                                                                "time_stamp": "07/07/2024 14:09:07",
                                                                "message": "Successfully retrieved revenue",
                                                                "data": {
                                                                  "content": [
                                                                    {
                                                                      "Delivery_order_id": 1,
                                                                      "User_name": "Vo VAN T",
                                                                      "Package_name": [
                                                                        "Combo Bò nướng mĩ vị Tailor",
                                                                        "Lẩu Tứ Xuyên"
                                                                      ],
                                                                      "Delivery_order_status": "Delivered",
                                                                      "Delivery_order_date": "2024-07-01T05:49:21.000+00:00",
                                                                      "Delivery_shipped_date": "2024-07-01T06:50:39.000+00:00",
                                                                      "Shipper_name": "Shipper Tinh",
                                                                      "Delivery_order_value": 1400000
                                                                    },
                                                                    {
                                                                      "Delivery_order_id": 2,
                                                                      "User_name": "Vo VAN T",
                                                                      "Package_name": [
                                                                        "Combo Bò nướng mĩ vị Tailor"
                                                                      ],
                                                                      "Delivery_order_status": "Delivered",
                                                                      "Delivery_order_date": "2024-07-01T05:49:24.000+00:00",
                                                                      "Delivery_shipped_date": "2024-07-01T06:50:48.000+00:00",
                                                                      "Shipper_name": "Shipper Tinh",
                                                                      "Delivery_order_value": 400000
                                                                    }
                                                                  ],
                                                                  "page_no": 0,
                                                                  "page_size": 10,
                                                                  "total_elements": 2,
                                                                  "total_pages": 1,
                                                                  "last": true
                                                                }
                                                              }
                                            """))),
                    @ApiResponse(responseCode = "400", description = "Fail to retrieve revenue by period")
            }
    )
    @GetMapping(value = "/revenue-details")
    public ResponseEntity<Object> revenueDetails(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "orderDate", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(name = "period", defaultValue = "daily", required = false) String period,
            @RequestParam(name = "date", defaultValue = "", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        if (date == null) {
            date = LocalDate.now();
        }
        return deliveryOrderService.getDeliveryOrderDetailByAdmin(pageNo, pageSize, sortBy, sortDir, period, date);
    }

    @Operation(
            summary = "Get shipper's list of delivery order",
            description = "Get shipper's list of delivery order to show in admin panel . \n" +
                    "Default sorted by id.",
            tags = {"Admin"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "shipper's list of delivery order retrieve successfully",
                            content = @Content(
                                    examples = @ExampleObject(value = """
                                            {
                                              "http_status": 200,
                                              "time_stamp": "07/12/2024 18:20:46",
                                              "message": "Successfully retrieved Delivery Order Detail by shipper",
                                              "data": {
                                                "content": [
                                                  {
                                                    "Delivery_order_id": 1,
                                                    "User_name": "dinh tai",
                                                    "Package_name": [
                                                      "Combo Nướng GoGi Combo Sườn bò Mỹ bỏ xương & Thăn nội bò Mỹ Mua 1 Tặng 1"
                                                    ],
                                                    "Delivery_order_status": "Delivered",
                                                    "Delivery_order_date": "2024-07-10T14:19:44.000+00:00",
                                                    "Delivery_shipped_date": "2024-07-10T15:20:03.000+00:00",
                                                    "Shipper_name": "Shipper Tinh",
                                                    "Delivery_order_value": 594000
                                                  }
                                                ],
                                                "page_no": 0,
                                                "page_size": 10,
                                                "total_elements": 2,
                                                "total_pages": 1,
                                                "last": true
                                              }
                                            }
                                            """))),
                    @ApiResponse(responseCode = "400", description = "Fail to retrieve shipper's list of delivery order")
            }
    )
    @GetMapping(value = "/shipper-tracking/detail")
    public ResponseEntity<Object> shipperTrackingDetail(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(name = "shipperId", required = true) int shipperId

    ) {
        return deliveryOrderService.getDeliveryOrderDetailByShipperId(pageNo, pageSize, sortBy, sortDir, shipperId);
    }

    @Operation(
            summary = "Get list of shippers by admin",
            description = "Get list of shippers that showing total order in admin panel . \n" +
                    "Default sorted by id.",
            tags = {"Admin"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "list of shippers retrieve successfully",
                            content = @Content(
                                    examples = @ExampleObject(value = """
                                              {
                                                     "Delivery_Order_Id": 7,
                                                     "User_name": "dinh tai",
                                                     "Phone": "0977833674",
                                                     "Package_name": [
                                                       "Combo nướng lẩu GoGi - Combo GoGi Smart",
                                                       "Combo nướng lẩu GoGi - Combo Daehak"
                                                     ],
                                                     "Order_value": 1298000,
                                                     "Address": "asdasda",
                                                     "Latitude": 21.123214,
                                                     "Longitude": 101.123123,
                                                     "Order_date": "2024-07-16 17:23:26.0",
                                                     "Order_status": "Preparing"
                                                   }
                                                 ],
                                                 "page_no": 0,
                                                 "page_size": 10,
                                                 "total_elements": 2,
                                                 "total_pages": 1,
                                                 "last": true
                                               }
                                            """))),
                    @ApiResponse(responseCode = "400", description = "Fail to retrieve list of shippers")
            }
    )
    @GetMapping(value = "/shipper-tracking")
    public ResponseEntity<Object> shipperTracking(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return shipperService.getAllShippersByAdmin(pageNo, pageSize, sortBy, sortDir);
    }




}
