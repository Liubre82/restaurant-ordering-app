package com.restaurant.restaurantorderingapp.controllers.foodControllers;

import com.restaurant.restaurantorderingapp.dto.foodSizesDto.CreateFoodSizeDTO;
import com.restaurant.restaurantorderingapp.dto.foodSizesDto.FoodSizeDTO;
import com.restaurant.restaurantorderingapp.dto.foodSizesDto.UpdateFoodSizeDTO;
import com.restaurant.restaurantorderingapp.services.foodServices.FoodSizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodSizeController {

    private final FoodSizeService foodSizeService;

    @Autowired
    public FoodSizeController(FoodSizeService foodSizeService) {
        this.foodSizeService = foodSizeService;
    }

    // curl -i -s http://localhost:8080/api/foodSizes | sed -e 's/{/\n&/g'
    @GetMapping("/public/foodSizes")
    public ResponseEntity<List<FoodSizeDTO>> getFoodSizes() {
        List<FoodSizeDTO> foodSizes = foodSizeService.getAllFoodSizes();
        return ResponseEntity.ok(foodSizes);
    }

    @GetMapping("/public/foodSizes/search")
    public ResponseEntity<List<FoodSizeDTO>> searchFoodSizes(
            @RequestParam String searchInput) {
        List<FoodSizeDTO> foodSizes = foodSizeService.searchFoodSizes(searchInput);
        return foodSizes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(foodSizes);
    }

    @GetMapping("/public/foodSizes/{foodSizeId}")
    public ResponseEntity<FoodSizeDTO> getFoodSize(@PathVariable Long foodSizeId) {
        FoodSizeDTO foodSizeDTO = foodSizeService.getFoodSizeById(foodSizeId);
        return ResponseEntity.ok(foodSizeDTO);
    }

    // curl -i -X POST -H "Content-Type: application/json" -d '{"foodSizeName": "XXL"}' http://localhost:8080/api/foodSizes
    @PostMapping("/admin/foodSizes")
    public ResponseEntity<String> createFoodSize(
            @RequestBody @Valid CreateFoodSizeDTO CreateFoodSizeDTO) {
        foodSizeService.createFoodSizes(CreateFoodSizeDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Food Size created successfully.");
    }

    // curl -i -X DELETE http://localhost:8080/api/foodSizes/7
    @DeleteMapping("/admin/foodSizes/{foodSizeId}")
    public ResponseEntity<String> deleteFoodSize(@PathVariable Long foodSizeId) {
        foodSizeService.deleteFoodSize(foodSizeId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Food Size deleted successfully.");
    }

    @PutMapping("/admin/foodSizes/{foodSizeId}")
    public ResponseEntity<FoodSizeDTO> updateFoodSize(
            @PathVariable Long foodSizeId, @RequestBody @Valid UpdateFoodSizeDTO updateFoodSizeDTO) {
        FoodSizeDTO foodSizeDTO = foodSizeService.updateFoodSize(foodSizeId, updateFoodSizeDTO);
        return ResponseEntity.ok(foodSizeDTO);
    }
}
