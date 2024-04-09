package com.restaurant.restaurantorderingapp.controllers.foodControllers;

import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.CreateFoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.FoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.UpdateFoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.services.foodServices.FoodItemVariationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodItemVariationController {

    private final String entityName = "Food Item Variation";

    private final FoodItemVariationService foodItemVariationService;

    @Autowired
    public FoodItemVariationController(FoodItemVariationService foodItemVariationService) {
        this.foodItemVariationService = foodItemVariationService;
    }

    // curl -i -s http://localhost:8080/api/foodItemVariations | sed -e 's/{/\n&/g'
    @GetMapping("/public/foodItemVariations")
    public ResponseEntity<List<FoodItemVariationDTO>> getFoodItemVariations() {
        List<FoodItemVariationDTO> foodItemVariations = foodItemVariationService.getAllFoodItemVariations();
        return ResponseEntity.ok(foodItemVariations);
    }

    @GetMapping("/public/foodItemVariations/{foodItemVariationId}")
    public ResponseEntity<FoodItemVariationDTO> getFoodItemVariation(@PathVariable Long foodItemVariationId) {
        FoodItemVariationDTO foodItemVariationDTO = foodItemVariationService.getFoodItemVariationById(foodItemVariationId);
        return ResponseEntity.ok(foodItemVariationDTO);
    }

    // curl -i -X POST -H "Content-Type: application/json" -d '{"foodItemVariationName": "XXL"}' http://localhost:8080/api/foodItemVariations
    @PostMapping("/admin/foodItemVariations")
    public ResponseEntity<String> createFoodItemVariation(
            @RequestBody @Valid CreateFoodItemVariationDTO CreateFoodItemVariationDTO) {
        foodItemVariationService.createFoodItemVariation(CreateFoodItemVariationDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entityName + " created successfully.");
    }

    // curl -i -X DELETE http://localhost:8080/api/foodItemVariations/7
    @DeleteMapping("/admin/foodItemVariations/{foodItemVariationId}")
    public ResponseEntity<String> deleteFoodItemVariation(@PathVariable Long foodItemVariationId) {
        foodItemVariationService.deleteFoodItemVariation(foodItemVariationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityName + " deleted successfully.");
    }

    @PutMapping("/admin/foodItemVariations/{foodItemVariationId}")
    public ResponseEntity<FoodItemVariationDTO> updateFoodItemVariation(
            @PathVariable Long foodItemVariationId, @RequestBody @Valid UpdateFoodItemVariationDTO updateFoodItemVariationDTO) {
        FoodItemVariationDTO foodItemVariationDTO = foodItemVariationService.updateFoodItemVariation(foodItemVariationId, updateFoodItemVariationDTO);
        return ResponseEntity.ok(foodItemVariationDTO);
    }
}
