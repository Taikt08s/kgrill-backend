package com.group2.kgrill.controller;

import com.swd392.group2.kgrill_service.dto.DeliveryLocationDTO;
import com.swd392.group2.kgrill_service.exception.CustomSuccessHandler;
import com.swd392.group2.kgrill_service.service.DeliveryOrderService;
import com.swd392.group2.kgrill_service.service.PackageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("food-package")
@RequiredArgsConstructor
@Tag(name = "Food Package", description = "Controller responsible for handling Food Package operations")
public class PackageController {

    private final PackageService packageService;

    @Operation(
            summary = "Get food package list",
            description = "Get all current food package",
            tags = {"Food Package"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get food package list successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to get food package list"),
    })
    @PostMapping(value = "/search")
    public ResponseEntity<Object> updateDeliveryOrderLocation(@Parameter(description = "Page number, starting from 1", required = true) @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                                              @Parameter(description = "Page size, 10 students max", required = true) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                              @Parameter(description = "Sort field default by Id", required = true) @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                                              @Parameter(description = "Sort by ascending or descending") @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
//                                                              @Parameter(description = "Filter by name/size/") @RequestParam(name = "filter", required = false) String filter,
                                                              @Parameter(description = "Search keyword") @RequestParam(value = "value", required = false) String value) {

        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully get package list", packageService.GetAllPackagePages(pageNumber, pageSize, sortField, sortDir));
    }
}
