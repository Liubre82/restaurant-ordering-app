package com.restaurant.restaurantorderingapp.services;

import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.CreateFoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.FoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.UpdateFoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.DuplicateKeyException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.models.food.FoodItem;
import com.restaurant.restaurantorderingapp.models.food.FoodItemVariation;
import com.restaurant.restaurantorderingapp.models.food.FoodSize;
import com.restaurant.restaurantorderingapp.repositories.FoodItemRepository;
import com.restaurant.restaurantorderingapp.repositories.FoodItemVariationRepository;
import com.restaurant.restaurantorderingapp.repositories.FoodSizeRepository;
import com.restaurant.restaurantorderingapp.utils.mappers.FoodItemVariationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.restaurant.restaurantorderingapp.utils.mappers.FoodItemVariationMapper.fromDTOToEntity;
import static com.restaurant.restaurantorderingapp.utils.mappers.FoodItemVariationMapper.fromEntityToDTO;

@Service
public class FoodItemVariationService {

    private final FoodItemVariationRepository foodItemVariationRepository;

    private final FoodItemRepository foodItemRepository;

    private final FoodSizeRepository foodSizeRepository;

    @Autowired
    public FoodItemVariationService(
            FoodItemVariationRepository foodItemVariationRepository,
            FoodItemRepository foodItemRepository,
            FoodSizeRepository foodSizeRepository) {
        this.foodItemVariationRepository = foodItemVariationRepository;
        this.foodItemRepository = foodItemRepository;
        this.foodSizeRepository = foodSizeRepository;
    }

    public FoodItem findFoodItemById(String foodItemId) {
        return foodItemRepository.findById(foodItemId)
                .orElseThrow(() -> new NotFoundException("Food Item", foodItemId));
    }

    public FoodSize findFoodSizeById(Long foodSizeId) {
        return foodSizeRepository.findById(foodSizeId)
                .orElseThrow(() -> new NotFoundException("Food Size", foodSizeId));
    }

    public FoodItemVariation findFoodItemVariationById(Long foodItemVariationId) {
        return foodItemVariationRepository.findById(foodItemVariationId)
                .orElseThrow(() -> new NotFoundException("Food Item Variation", foodItemVariationId));
    }

    // Route service methods below

    public FoodItemVariationDTO getFoodItemVariationById(Long foodItemVariationId) {
        FoodItemVariation foodItemVariation = findFoodItemVariationById(foodItemVariationId);
        return fromEntityToDTO(foodItemVariation);

    }

    public List<FoodItemVariationDTO> getAllFoodItemVariations() {
        String entityName = "food item variations";
        List<FoodItemVariation> foodItemVariations = (List<FoodItemVariation>) foodItemVariationRepository.findAll();
        if(foodItemVariations.isEmpty()) throw new EmptyDataTableException(entityName);
        return foodItemVariations.stream()
                .map(FoodItemVariationMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public void createFoodItemVariation(CreateFoodItemVariationDTO createFoodItemVariationDTO) {
        boolean categoryNameCheck = foodItemVariationRepository.checkFoodItemVariationUniqueness(createFoodItemVariationDTO.foodItemId(), createFoodItemVariationDTO.foodSizeId());
        if(categoryNameCheck) throw new DuplicateKeyException();
        FoodItemVariation foodItemVariation = fromDTOToEntity(
                createFoodItemVariationDTO,
                findFoodItemById(createFoodItemVariationDTO.foodItemId()),
                findFoodSizeById(createFoodItemVariationDTO.foodSizeId())
                );
        foodItemVariationRepository.save(foodItemVariation);
    }

    public void deleteFoodItemVariation(Long foodItemVariationId) throws NotFoundException {
        boolean FoodItemVariationExist = foodItemVariationRepository.existsById(foodItemVariationId);
        if(!FoodItemVariationExist) throw new NotFoundException("Food Item Variation", foodItemVariationId);
        foodItemVariationRepository.deleteById(foodItemVariationId);
    }

    public FoodItemVariationDTO updateFoodItemVariation(Long foodItemVariationId, UpdateFoodItemVariationDTO updateFoodItemVariationDTO) {
        FoodItemVariation foodItemVariation = findFoodItemVariationById(foodItemVariationId);
        FoodItem foodItem = findFoodItemById(updateFoodItemVariationDTO.foodItemId());
        FoodSize foodSize = findFoodSizeById(updateFoodItemVariationDTO.foodSizeId());

        foodItemVariation.setFoodItem(foodItem);
        foodItemVariation.setFoodSize(foodSize);
        foodItemVariation.setFoodPrice(updateFoodItemVariationDTO.foodPrice());

        foodItemVariationRepository.save(foodItemVariation);
        FoodItemVariationDTO FoodItemVariationDTOUpdated = fromEntityToDTO(foodItemVariation);
        return FoodItemVariationDTOUpdated;
    }
}
