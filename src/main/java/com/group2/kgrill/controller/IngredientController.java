package com.group2.kgrill.controller;


import com.swd392.group2.kgrill_service.dto.IngredientDTO;
import com.swd392.group2.kgrill_service.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Tag(name = "Ingredient", description = "Controller responsible for handling ingredient operations")
public class IngredientController {
    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    //    @GetMapping("dish")
//    public ResponseEntity<List<DishDTO>> GetDishes(){
//
//        return new ResponseEntity<>(dishService.getAllDish(), HttpStatus.OK);
//    }

    @GetMapping("ingredient/{id}")
    public ResponseEntity<IngredientDTO> ingredient(@PathVariable("id") int id){
        return ResponseEntity.ok(ingredientService.getIngredientByID(id));
    }
    @Operation(
            summary = "Create new ingredient",
            description = "Create new ingredient for manager",
            tags = {"Ingredient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create new ingredient successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to create new ingredient"),
    })
    @PostMapping("ingredient/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> ingredientCreate(@RequestBody IngredientDTO ingredientDTO){
        ingredientService.createIngredient(ingredientDTO);
        return new ResponseEntity<>("Create new ingredient successfully",HttpStatus.CREATED);
    }
    @Operation(
            summary = "Update ingredient",
            description = "Update ingredient for manager",
            tags = {"Ingredient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update ingredient successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to update ingredient"),
    })
    @PutMapping("ingredient/{id}/update")
    public ResponseEntity<Object> ingredientUpdate(@RequestBody IngredientDTO ingredientDTO, @PathVariable("id") int id){
        ingredientService.updateIngredient(ingredientDTO, id);
        return new ResponseEntity<>("Update ingredient successfully",HttpStatus.OK);
    }
    @DeleteMapping("ingredient/{id}/delete")
    public ResponseEntity<String> ingredientDelete(@PathVariable("id") int id){
        ingredientService.deleteIngredient(id);
        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }
}
