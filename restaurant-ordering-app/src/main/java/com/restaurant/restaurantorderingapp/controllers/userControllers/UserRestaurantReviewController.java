package com.restaurant.restaurantorderingapp.controllers.userControllers;

import com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto.CreateUserRestaurantReviewDTO;
import com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto.UpdateUserRestaurantReviewDTO;
import com.restaurant.restaurantorderingapp.dto.userRestaurantReviewsDto.UserRestaurantReviewDTO;
import com.restaurant.restaurantorderingapp.services.userServices.UserRestaurantReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userRestaurantReviews")
public class UserRestaurantReviewController {

    private final String entityName = "User Restaurant Review";

    private final UserRestaurantReviewService userRestaurantReviewService;

    @Autowired
    public UserRestaurantReviewController(UserRestaurantReviewService userRestaurantReviewService) {
        this.userRestaurantReviewService = userRestaurantReviewService;
    }

    // curl -i -s http://localhost:8080/api/userRestaurantReviewes | sed -e 's/{/\n&/g'
    //Method should not be accessible to any users,
    @GetMapping
    public ResponseEntity<List<UserRestaurantReviewDTO>> getUserRestaurantReviews() {
        List<UserRestaurantReviewDTO> userRestaurantReviewes = userRestaurantReviewService.getAllUserRestaurantReviews();
        return ResponseEntity.ok(userRestaurantReviewes);
    }

    @GetMapping("/{userRestaurantReviewId}")
    public ResponseEntity<UserRestaurantReviewDTO> getUserRestaurantReview(@PathVariable Long userRestaurantReviewId) {
        UserRestaurantReviewDTO userRestaurantReviewDTO = userRestaurantReviewService.getUserRestaurantReviewById(userRestaurantReviewId);
        return ResponseEntity.ok(userRestaurantReviewDTO);
    }

    // curl -i -X POST -H "Content-Type: application/json" -d '{"userRestaurantReviewName": "XXL"}' http://localhost:8080/api/userRestaurantReviewes
    @PostMapping
    public ResponseEntity<String> createUserRestaurantReview(
            @RequestBody @Valid CreateUserRestaurantReviewDTO CreateUserRestaurantReviewDTO) {
        userRestaurantReviewService.createUserRestaurantReview(CreateUserRestaurantReviewDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entityName + " created successfully.");
    }

    // curl -i -X DELETE http://localhost:8080/api/userRestaurantReviewes/7
    @DeleteMapping("/{userRestaurantReviewId}")
    public ResponseEntity<String> deleteUserRestaurantReview(@PathVariable Long userRestaurantReviewId) {
        userRestaurantReviewService.deleteUserRestaurantReview(userRestaurantReviewId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityName + " deleted successfully.");
    }

    @PutMapping("/{userRestaurantReviewId}")
    public ResponseEntity<UserRestaurantReviewDTO> updateUserRestaurantReview(
            @PathVariable Long userRestaurantReviewId, @RequestBody @Valid UpdateUserRestaurantReviewDTO updateUserRestaurantReviewDTO) {
        UserRestaurantReviewDTO userRestaurantReviewDTO = userRestaurantReviewService.updateUserRestaurantReview(userRestaurantReviewId, updateUserRestaurantReviewDTO);
        return ResponseEntity.ok(userRestaurantReviewDTO);
    }
}
