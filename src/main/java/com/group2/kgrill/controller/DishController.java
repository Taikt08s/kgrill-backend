package com.group2.kgrill.controller;


import com.swd392.group2.kgrill_service.dto.request.DishRequest;
import com.swd392.group2.kgrill_service.exception.CustomSuccessHandler;
import com.swd392.group2.kgrill_service.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Tag(name = "Dish", description = "Controller responsible for handling dish operations")
public class DishController {

    private DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @Operation(
            summary = "Get dish's list",
            description = "Get all current dishes",
            tags = {"Dish"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get dish's list successfully",
                    content = @Content(
                            examples = @ExampleObject(value = """
                                        {
                                           "http_status": 200,
                                           "time_stamp": "06/02/2024 17:25:41",
                                           "message": "Successfully retrieved dishes list information",
                                           "data": {
                                             "content": [
                                               {
                                                 "dish_id": "a9126139-3c11-47ba-8493-cf7e480c3645",
                                                 "dish_name": "Bò Mĩ",
                                                 "dish_price": "289999",
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
            @ApiResponse(responseCode = "400", description = "Failed to get dish's list"),
    })
    @PostMapping(value = "/dish/dish-list")
    public ResponseEntity<Object> getAllDishByManager(@Parameter(description = "Page number, starting from 1", required = true) @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                                      @Parameter(description = "Page size, 10 dishes max", required = true) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                      @Parameter(description = "Sort field default by Id", required = true) @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                                      @Parameter(description = "Sort by ascending or descending") @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
//                                                              @Parameter(description = "Filter by name/size/") @RequestParam(name = "filter", required = false) String filter,
                                                      @Parameter(description = "Search keyword") @RequestParam(value = "value", required = false) String value) {

        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully get package list", dishService.getAllDishes(pageNumber, pageSize, sortField, sortDir));
    }
    @Operation(
            summary = "Get dish",
            description = "Get all current dishes",
            tags = {"Dish"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get dish successfully",
                    content = @Content(
                            examples = @ExampleObject(value = """
                                        {
                                           "http_status": 200,
                                           "time_stamp": "06/02/2024 17:25:41",
                                           "message": "Successfully retrieved dish information",
                                           "data": {
                                             "content": [
                                               {
                                                 "dish_id": "a9126139-3c11-47ba-8493-cf7e480c3645",
                                                 "dish_name": "Bò Mĩ",
                                                 "dish_price": "289999",
                                                 }
                                                  ],
                                           }
                                         }
                                    """))),
            @ApiResponse(responseCode = "400", description = "Failed to get dish"),
    })
    @GetMapping("dish/dish-detail")
    public ResponseEntity<DishRequest> dish(@NotNull int id){
        return ResponseEntity.ok(dishService.getDishByID(id));
    }
    @Operation(
            summary = "Search dish",
            description = "Search dish by name and price",
            tags = {"Dish"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get dish successfully",
                    content = @Content(
                            examples = @ExampleObject(value = """
                                        {
                                           "http_status": 200,
                                           "time_stamp": "06/02/2024 17:25:41",
                                           "message": "Successfully retrieved dishes list information",
                                           "data": {
                                             "content": [
                                               {
                                                 "dish_id": "a9126139-3c11-47ba-8493-cf7e480c3645",
                                                 "dish_name": "Bò Mĩ",
                                                 "dish_price": "289999",
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
            @ApiResponse(responseCode = "400", description = "Failed to get dish"),
    })
    @GetMapping("dish/dish-details-by-filter")
    public ResponseEntity<Object> searchDishByFilter(@Parameter(description = "Page number, starting from 1", required = true) @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                                     @Parameter(description = "Page size, 10 dishes max", required = true) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                     @Parameter(description = "Sort field default by Id", required = true) @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                                     @Parameter(description = "Sort by ascending or descending") @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
//                                                              @Parameter(description = "Filter by name/size/") @RequestParam(name = "filter", required = false) String filter,
                                                     @Parameter(description = "Search keyword") @RequestParam(value = "keyword", required = false) String keyword,
                                                     @Parameter(description = "Search dish's category") @RequestParam(value="category", required = false) String category,
                                                     @Parameter(description = "Min Price") @RequestParam(value="minPrice", required = false) Double minPrice,
                                                     @Parameter(description = "Max price") @RequestParam(value="maxPrice", required = false) Double maxPrice){
        return ResponseEntity.ok(dishService.searchDishByFilter(pageNumber, pageSize, minPrice, maxPrice, sortField, sortDir, keyword, category));
    }
    @Operation(
            summary = "Create new dish",
            description = "Create new dish for manager",
            tags = {"Dish"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create new dish successfully",
                    content = @Content(
                            examples = @ExampleObject(value = """
                                        {
                                          "http_status": 200,
                                           "time_stamp": "06/02/2024 17:25:41",
                                           "message": "Create new dish successfully",
                                           "data": {
                                             "content": [
                                               {
                                                "dish_id": 0,
                                                      "dish_name": " Bò Nam Phi",
                                                      "dish_price": 99000,
                                                      "dish_category": {
                                                        "category_id": 0,
                                                        "category_name": "Dĩa rau"
                                                      },
                                                      "dish_ingredient_list": [
                                                        {
                                                          "ingredient_id": 0,
                                                          "ingredient_name": "Tôm"
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

            @ApiResponse(responseCode = "400", description = "Failed to create new dish"),
    })
    @PostMapping("dish/new-dish")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> dishCreate(@RequestBody DishRequest dishDTO){
        dishService.createDish(dishDTO);
        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Create a new dish successfully", "");
    }

    @Operation(
            summary = "Update dish",
            description = "Update dish for manager",
            tags = {"Dish"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update dish successfully",
                    content = @Content(
                            examples = @ExampleObject(value = """
                                        {
                                          "http_status": 200,
                                           "time_stamp": "06/02/2024 17:25:41",
                                           "message": "Create new dish successfully",
                                           "data": {
                                             "content": [
                                               {
                                                "dish_id": 0,
                                                      "dish_name": " Bò Nam Phi",
                                                      "dish_price": 99000,
                                                      "dish_category": {
                                                        "category_id": 0,
                                                        "category_name": "Dĩa rau"
                                                      },
                                                      "dish_ingredient_list": [
                                                        {
                                                          "ingredient_id": 0,
                                                          "ingredient_name": "Tôm"
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

            @ApiResponse(responseCode = "400", description = "Failed to update dish"),
    })

    @PutMapping("dish/")
    public ResponseEntity<Object> dishUpdate(@RequestBody DishRequest dishDTO){

        dishService.updateDish(dishDTO);
        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Updated dish successfully", "");
    }
    @Operation(
            summary = "Delete a dish",
            description = "Delete an existed dish",
            tags = {"Dish"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete dish successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to delete dish"),
    })
    @DeleteMapping("dish/{id}")
    public ResponseEntity<String> dishDelete(@PathVariable("id") int id){
        dishService.deleteDish(id);
        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }
}
