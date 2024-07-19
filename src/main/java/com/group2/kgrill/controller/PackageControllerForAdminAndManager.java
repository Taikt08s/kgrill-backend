package com.group2.kgrill.controller;

import com.swd392.group2.kgrill_service.dto.request.PackageRequest;
import com.swd392.group2.kgrill_service.exception.CustomSuccessHandler;
import com.swd392.group2.kgrill_service.service.CloudinaryUploadService;
import com.swd392.group2.kgrill_service.service.PackageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("food-package")
@RequiredArgsConstructor
@Tag(name = "Food Package", description = "Controller responsible for handling Food Package operations on Admin and Manager page")
public class PackageControllerForAdminAndManager {

    private final PackageService packageService;
    private final CloudinaryUploadService cloudinaryUploadConfig;

    @Operation(
            summary = "Get food package list",
            description = "Get all current food package",
            tags = {"Food Package"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get food package list successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to get food package list"),
    })
    @PostMapping(value = "/search-package")
    public ResponseEntity<Object> getAllFoodPackage(@Parameter(description = "Page number, starting from 1", required = true) @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                                    @Parameter(description = "Page size, 10 students max", required = true) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                    @Parameter(description = "Sort field default by Id", required = true) @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                                    @Parameter(description = "Sort by ascending or descending") @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
                                                    @Parameter(description = "Search keyword") @RequestParam(value = "value", required = false) String value) {

        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully get package list", packageService.getAllPackagePages(pageNumber, pageSize, sortField, sortDir));
    }

    @Operation(
            summary = "Add new food package",
            description = "Add a new food package",
            tags = {"Food Package"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Add a new package successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to add a new package"),
    })
    @PostMapping(value = "/add-package")
    public ResponseEntity<Object> addNewFoodPackage(@RequestBody @Valid PackageRequest pkgRequest) {
        packageService.addPackage(pkgRequest);
        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Add a new package successfully", "");
    }

    @Operation(
            summary = "Get details of a food package for update",
            description = "Get details of an existed food package for update",
            tags = {"Food Package"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get package detail successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to get package detail"),
    })
    @GetMapping(value = "/update-package")
    public ResponseEntity<Object> getFoodPackageDetail(@NotNull int pkgId) {
        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully get package detail", packageService.getAPackageDetail(pkgId));
    }

    @Operation(
            summary = "Update a food package",
            description = "Update an existed food package",
            tags = {"Food Package"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update food package successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to update food package"),
    })
    @PutMapping(value = "/update-package")
    public ResponseEntity<Object> updateAFoodPackage(@RequestBody @Valid PackageRequest pkgRequest) {
        packageService.updatePackage(pkgRequest);
        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully update package", "");
    }

    @Operation(
            summary = "Update food package thumbnail",
            description = "Update a food package's thumbnail",
            tags = {"Food Package"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update package thumbnail successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to update package thumbnail"),
    })
    @PostMapping(value = "/update-package-thumbnail", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> updateAFoodPackageThumbnail(@NotNull int packageId,
                                                              @RequestParam(value = "thumbnail_pic", required = false) MultipartFile thumbnailPicture) throws IOException {
        if (thumbnailPicture != null && !thumbnailPicture.isEmpty()) {
            String imageURL = cloudinaryUploadConfig.uploadFile(thumbnailPicture);
            packageService.uploadPackageThumbnail(packageId, imageURL);
        }
        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully update package thumbnail", "");
    }

    @Operation(
            summary = "Delete a food package",
            description = "Delete an existed food package",
            tags = {"Food Package"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete food package successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to delete food package"),
    })
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteAFoodPackage(@PathVariable("id") int pkgId) {
        packageService.deletePackageById(pkgId);
        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully delete package", "");
    }
}
