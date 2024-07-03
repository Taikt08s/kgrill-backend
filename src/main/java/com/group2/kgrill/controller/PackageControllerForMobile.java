package com.group2.kgrill.controller;

import com.swd392.group2.kgrill_service.exception.CustomSuccessHandler;
import com.swd392.group2.kgrill_service.service.PackageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mobile/food-package")
@RequiredArgsConstructor
@Tag(name = "Food Package Mobile", description = "Controller responsible for handling Food Package operations on mobile")
public class PackageControllerForMobile {

    private final PackageService packageService;

    @Operation(
            summary = "Get food package list on mobile",
            description = "Get all current food package on mobile",
            tags = {"Food Package Mobile"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get food package list successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to get food package list"),
    })
    @PostMapping(value = "/search")
    public ResponseEntity<Object> getAllFoodPackageOnMobile(
//                                                              @Parameter(description = "Filter by name/size/") @RequestParam(name = "filter", required = false) String filter,
                                                    @Parameter(description = "Search keyword") @RequestParam(value = "value", required = false) String value) {

        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully get package list", packageService.getAllPackageOnMobile());
    }

    @Operation(
            summary = "Get package detail on mobile",
            description = "Get food package detail on mobile",
            tags = {"Food Package Mobile"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get food package detail successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to get food package detail"),
    })
    @PostMapping(value = "/search")
    public ResponseEntity<Object> getFoodPackageDetailOnMobile(
//                                                              @Parameter(description = "Filter by name/size/") @RequestParam(name = "filter", required = false) String filter,
                                                    @Parameter(description = "Search keyword") @RequestParam(value = "value", required = false) String value) {

        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully get package list", packageService.getAllPackageOnMobile());
    }
}
