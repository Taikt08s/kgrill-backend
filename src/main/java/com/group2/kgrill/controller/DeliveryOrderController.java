package com.group2.kgrill.controller;

import com.swd392.group2.kgrill_service.dto.DeliveryLocationDTO;
import com.swd392.group2.kgrill_service.service.DeliveryOrderService;
import com.swd392.group2.kgrill_service.util.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("delivery-order")
@RequiredArgsConstructor
@Tag(name = "Delivery Order", description = "Controller responsible for handling delivery order operations")
public class DeliveryOrderController {

    private final DeliveryOrderService deliveryOrderService;

    @Operation(
            summary = "Update delivery order location",
            description = "Update current location in Delivery order after user choose location",
            tags = {"Delivery Order"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update delivery order location successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to update delivery order location"),
    })
    @PostMapping(value = "/location")
    public ResponseEntity<Object> updateDeliveryOrderLocation(@NotNull Long id,
                                                              @RequestBody DeliveryLocationDTO deliveryLocationRequest) {
        if (deliveryLocationRequest != null && id != null) {
            deliveryOrderService.updateDeliveryOrderLocation(id, deliveryLocationRequest);
            return ResponseEntity.ok().body("Delivery order location updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update delivery order location");
        }
    }

    @Operation(
            summary = "View Ordering queue by Manager ",
            description = "View all user's order information. user's name, address, package name,.... ",
            tags = {"Delivery Order"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordering queue information retrieve successfully",
                    content = @Content(
                            examples = @ExampleObject(value = """
                                       {
                                            "http_status": 200,
                                            "time_stamp": "07/16/2024 20:51:51",
                                            "message": "Successfully retrieved User's ordering for Manager ",
                                            "data": {
                                              "content": [
                                                {
                                                  "address": "asdasda",
                                                  "latitude": 21.123214,
                                                  "longitude": 101.123123,
                                                  "phone": "0977833674",
                                                  "User_name": "dinh tai",
                                                  "Package_name": [
                                                    "Combo nướng lẩu GoGi - Combo GoGi Smart",
                                                    "Combo nướng lẩu GoGi - Combo Daehak"
                                                  ],
                                                  "Order_value": 1298000,
                                                  "Order_date": "2024-07-16 17:23:26.0",
                                                  "Order_status": "Ordering"
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
            @ApiResponse(responseCode = "401", description = "You have no permission to access this page"),
    })
    @GetMapping(value = "/ordering")
    public ResponseEntity<Object> getOrderingQueue(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return deliveryOrderService.getDeliveryOrderByStatus(pageNo, pageSize, sortBy, sortDir, "Preparing");
    }




}