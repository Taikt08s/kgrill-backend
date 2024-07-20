package com.group2.kgrill.controller;

import com.swd392.group2.kgrill_service.exception.CustomSuccessHandler;
import com.swd392.group2.kgrill_service.service.DeliveryOrderService;
import com.swd392.group2.kgrill_service.service.ShipperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("shipper")
@RequiredArgsConstructor
@Tag(name = "Shipper", description = "Controller responsible for handling Shipper operations For Manager")
public class ShipperController {

    private final ShipperService shipperService;

    @Operation(
            summary = "Get available shipper list for Manager",
            description = "Get available shipper list for Manager",
            tags = {"Shipper"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get shipper list successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to get shipper list"),
    })
    @GetMapping("/available-shippers")
    public ResponseEntity<Object> getAvailableShipperList(@Parameter(description = "Page number, starting from 1", required = true) @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                                          @Parameter(description = "Page size, 10 students max", required = true) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                          @Parameter(description = "Sort field default by Id", required = true) @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                                          @Parameter(description = "Sort by ascending or descending") @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir) {

        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully get shipper list", shipperService.getAvailableShipperList(pageNumber, pageSize, sortField, sortDir));
    }

    @Operation(
            summary = "Assign a shipper to an order",
            description = "Assign an available shipper to a 'Preparing' order",
            tags = {"Shipper"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assign shipper to order successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to assign shipper to order"),
    })
    @PostMapping(value = "/order")
    public ResponseEntity<Object> getCartDetail(@NotNull long shipperId, @NotNull long orderId) {

        if (shipperService.assignShipperToOrder(shipperId, orderId)) {
            return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully assign shipper to order", "");
        }
        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Failed to assign shipper to order", "");
    }
}
