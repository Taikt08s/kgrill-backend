package com.group2.kgrill.controller;


import com.swd392.group2.kgrill_service.dto.DishDTO;
import com.swd392.group2.kgrill_service.exception.CustomSuccessHandler;
import com.swd392.group2.kgrill_service.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Tag(name = "Dish", description = "Controller responsible for handling dish operations")
public class DishController {

    private DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    //    @GetMapping("dish")
//    public ResponseEntity<List<DishDTO>> GetDishes(){
//
//        return new ResponseEntity<>(dishService.getAllDish(), HttpStatus.OK);
//    }
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
    @PostMapping(value = "/dish/search")
    public ResponseEntity<Object> getAllDishByManager(@Parameter(description = "Page number, starting from 1", required = true) @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                                      @Parameter(description = "Page size, 10 dishes max", required = true) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                      @Parameter(description = "Sort field default by Id", required = true) @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                                      @Parameter(description = "Sort by ascending or descending") @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
//                                                              @Parameter(description = "Filter by name/size/") @RequestParam(name = "filter", required = false) String filter,
                                                      @Parameter(description = "Search keyword") @RequestParam(value = "value", required = false) String value) {

        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully get package list", dishService.getAllDishes(pageNumber, pageSize, sortField, sortDir));
    }
    @Operation(
            summary = "Get dish's list",
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
    @GetMapping("dish/{id}")
    public ResponseEntity<DishDTO> dish(@PathVariable int id){
        return ResponseEntity.ok(dishService.getDishByID(id));
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

            @ApiResponse(responseCode = "400", description = "Failed to create new dish"),
    })
    @PostMapping("dish/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DishDTO> dishCreate(@RequestBody DishDTO dishDTO){

        return new ResponseEntity<>(dishService.createDish(dishDTO),HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update dish",
            description = "Update dish for manager",
            tags = {"Dish"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update dish successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to update dish"),
    })

    @PutMapping("dish/{id}/update")
    public ResponseEntity<Object> dishUpdate(@RequestBody DishDTO dishDTO, @PathVariable("id") int id){

        dishService.updateDish(dishDTO, id);
        return new ResponseEntity<>("Update dish successfully",HttpStatus.OK);
    }

    @DeleteMapping("dish/{id}/delete")
    public ResponseEntity<String> dishDelete(@PathVariable("id") int id){
        dishService.deleteDish(id);
        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }
}
