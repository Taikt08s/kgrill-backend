package com.group2.kgrill.controller;


import com.swd392.group2.kgrill_service.dto.IngredientDTO;
import com.swd392.group2.kgrill_service.exception.CustomSuccessHandler;
import com.swd392.group2.kgrill_service.service.IngredientService;
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

@RestController
@RequestMapping("/")
@Tag(name = "Ingredient", description = "Controller responsible for handling ingredient operations")
public class IngredientController {
    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @Operation(
            summary = "Get ingredient's list",
            description = "Get all current ingredients",
            tags = {"Ingredient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get ingredient's list successfully",
                    content = @Content(
                            examples = @ExampleObject(value = """
                                        {
                                           "http_status": 200,
                                           "time_stamp": "06/02/2024 17:25:41",
                                           "message": "Successfully retrieved ingredient information",
                                           "data": {
                                             "content": [
                                               {
                                                 "ingredient_id": "a9126139-3c11-47ba-8493-cf7e480c3645",
                                                 "ingredient_name": "Tiêu xanh",
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
            @ApiResponse(responseCode = "400", description = "Failed to get ingredient's list"),
    })
    @PostMapping(value = "/search/ingredient")
    public ResponseEntity<Object> getAllIngredientByManager(@Parameter(description = "Page number, starting from 1", required = true) @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                                            @Parameter(description = "Page size, 10 dishes max", required = true) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                            @Parameter(description = "Sort field default by Id", required = true) @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                                            @Parameter(description = "Sort by ascending or descending") @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
//                                                              @Parameter(description = "Filter by name/size/") @RequestParam(name = "filter", required = false) String filter,
                                                            @Parameter(description = "Search keyword") @RequestParam(value = "value", required = false) String value) {

        return CustomSuccessHandler.responseBuilder(HttpStatus.OK, "Successfully get package list", ingredientService.getAllIngredients(pageNumber, pageSize, sortField, sortDir));
    }
    //    @GetMapping("dish")
//    public ResponseEntity<List<DishDTO>> GetDishes(){
//
//        return new ResponseEntity<>(dishService.getAllDish(), HttpStatus.OK);
//    }
    @Operation(
            summary = "Get ingredient",
            description = "Get current ingredient",
            tags = {"Ingredient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get ingredient successfully",
                    content = @Content(
                            examples = @ExampleObject(value = """
                                        {
                                           "http_status": 200,
                                           "time_stamp": "06/02/2024 17:25:41",
                                           "message": "Successfully retrieved ingredient information",
                                           "data": {
                                             "content": [
                                               {
                                                 "ingredient_id": "a9126139-3c11-47ba-8493-cf7e480c3645",
                                                 "ingredient_name": "Tiêu xanh",
                                                 }
                                                  ],
                                           }
                                         }
                                    """))),
            @ApiResponse(responseCode = "400", description = "Failed to get ingredient"),
    })

    @GetMapping("ingredient/{id}")
    public ResponseEntity<IngredientDTO> ingredient(@PathVariable("id") int id){
        return ResponseEntity.ok(ingredientService.getIngredientByID(id));
    }
    @Operation(
            summary = "Create new ingredient",
            description = "Create new ingredient for manager",
            tags = {"Ingredient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create new ingredient successfully",
                    content = @Content(
                            examples = @ExampleObject(value = """
                                        {
                                           "http_status": 200,
                                           "time_stamp": "06/02/2024 17:25:41",
                                           "message": "Create new ingredient successfully",
                                           "data": {
                                             "content": [
                                               {
                                                 "ingredient_id": "a9126139-3c11-47ba-8493-cf7e480c3645",
                                                 "ingredient_name": "Tiêu xanh",
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
