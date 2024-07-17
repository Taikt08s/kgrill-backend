package com.group2.kgrill.controller;

import com.swd392.group2.kgrill_service.dto.DeliveryLocationDTO;
import com.swd392.group2.kgrill_service.exception.CustomSuccessHandler;
import com.swd392.group2.kgrill_service.service.DeliveryOrderService;
import io.swagger.v3.oas.annotations.Operation;
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
    @GetMapping("/cancel-order/{orderId}")
    public ResponseEntity<Object> cancelOrderForManager(@PathVariable Long orderId){
        return deliveryOrderService.cancelOrderForManager(orderId);
    }

    @Operation(
            summary = "Get cart detail",
            description = "Get cart detail after succeed login",
            tags = {"Delivery Order"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get cart detail successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to get cart detail"),
    })
    @GetMapping(value = "/cart-detail")
    public ResponseEntity<Object> getCartDetail(@NotNull UUID userId) {
        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully get cart detail", deliveryOrderService.getOrderDetailAfterLogin(userId));
    }

    @Operation(
            summary = "Add a package to cart",
            description = "Add a available package to cart",
            tags = {"Delivery Order"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "add to cart successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to add to cart"),
    })
    @PostMapping(value = "/add-package")
    public ResponseEntity<Object> addAFoodPackageToCart(@NotNull UUID userId, @NotNull int packageId, @NotNull int quantity) {
        deliveryOrderService.addPackageToDeliveryOrder(userId, packageId, quantity);
        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully add to cart", null);
    }

    @Operation(
            summary = "Update cart detail",
            description = "Update cart detail",
            tags = {"Delivery Order"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update cart detail successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to update cart detail"),
    })
    @PostMapping(value = "/update-order-detail")
    public ResponseEntity<Object> updateCartDetail(@NotNull int orderDetailId, @NotNull int quantity) {
        deliveryOrderService.updateOrderDetail(orderDetailId, quantity);
        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully update cart detail", null);
    }
}