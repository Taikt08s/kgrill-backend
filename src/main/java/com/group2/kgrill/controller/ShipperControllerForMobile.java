package com.group2.kgrill.controller;

import com.swd392.group2.kgrill_service.exception.CustomSuccessHandler;
import com.swd392.group2.kgrill_service.service.DeliveryOrderService;
import com.swd392.group2.kgrill_service.service.ShipperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shipper-mobile")
@RequiredArgsConstructor
@Tag(name = "Shipper Mobile", description = "Controller responsible for handling Shipper operations For Mobile")
public class ShipperControllerForMobile {

    private final ShipperService shipperService;
    private final DeliveryOrderService deliveryOrderService;

    @Operation(
            summary = "Get delivered order history of shipper",
            description = "Get order history delivered by a shipper",
            tags = {"Shipper Mobile"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get delivered order history of this shipper successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to get delivered order history of this shipper"),
    })
    @GetMapping(value = "/order-history-of-shipper")
    public ResponseEntity<Object> getOrderHistoryShipper(@NotNull int shipperId) {

        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully get delivered order history of this shipper", deliveryOrderService.getOrderHistoryBasedOnShipperId(shipperId));
    }
}
