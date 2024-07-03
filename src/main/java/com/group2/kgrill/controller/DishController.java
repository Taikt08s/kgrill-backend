package com.group2.kgrill.controller;


import com.swd392.group2.kgrill_service.dto.DishDTO;
import com.swd392.group2.kgrill_service.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
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

    @GetMapping("dish/{id}")
    public ResponseEntity<DishDTO> dish(@PathVariable int id){
        return ResponseEntity.ok(dishService.getDishByID(id));
    }
    @Operation(
            summary = "Create new dish",
            description = "Create new dish for manager",
            tags = {"Dish"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create new dish successfully"),
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
