package com.restaurant.restaurantorderingapp.services;

import com.restaurant.restaurantorderingapp.dto.foodImagesDto.FoodImageDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemVariationsDto.FoodItemVariationDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemsDto.CreateFoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemsDto.FoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.foodItemsDto.UpdateFoodItemDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.DuplicateKeyException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.models.food.FoodImage;
import com.restaurant.restaurantorderingapp.models.food.FoodItem;
import com.restaurant.restaurantorderingapp.models.food.FoodItemVariation;
import com.restaurant.restaurantorderingapp.models.food.MenuCategory;
import com.restaurant.restaurantorderingapp.repositories.FoodItemRepository;
import com.restaurant.restaurantorderingapp.repositories.MenuCategoryRepository;
import com.restaurant.restaurantorderingapp.utils.mappers.FoodImageMapper;
import com.restaurant.restaurantorderingapp.utils.mappers.FoodItemVariationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.restaurant.restaurantorderingapp.utils.mappers.FoodItemMapper.fromDTOToEntity;
import static com.restaurant.restaurantorderingapp.utils.mappers.FoodItemMapper.fromEntityToDTO;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final MenuCategoryRepository menuCategoryRepository;

    @Autowired
    public FoodItemService(FoodItemRepository foodItemRepository, MenuCategoryRepository menuCategoryRepository) {
        this.foodItemRepository = foodItemRepository;
        this.menuCategoryRepository = menuCategoryRepository;
    }

    public MenuCategory findMenuCategoryById(Long menuCategoryId) {
        return menuCategoryRepository.findById(menuCategoryId)
                .orElseThrow(() -> new NotFoundException("Menu Category", menuCategoryId));
    }

    public FoodItem findFoodItemById(String foodItemId) {
        return foodItemRepository.findById(foodItemId)
                .orElseThrow(() -> new NotFoundException("Food Item", foodItemId));
    }

    public FoodItemDTO getFoodItemById(String foodItemId) {
        FoodItem foodItem = findFoodItemById(foodItemId);
        MenuCategory menuCategory = findMenuCategoryById(foodItem.getMenuCategory().getMenuCategoryId());
        FoodItemDTO FoodItemDTO = fromEntityToDTO(foodItem, menuCategory);
        return  FoodItemDTO;
    }

    /**
     * Retrieve all entities from food_items table then pass it into a
     * stream to be converted into DTOs to be sent to the client.
     *
     * @return the list of foodItems in DTO form.
     * @throws EmptyDataTableException if there is no foodItems in the datatable, AKA empty datatable.
     */
    public List<FoodItemDTO> getAllFoodItems() {
        String entityName = "food items";
        List<FoodItem> foodItems = (List<FoodItem>) foodItemRepository.findAll();
        if(foodItems.isEmpty()) throw new EmptyDataTableException(entityName);
        return foodItems.stream()
                .map(entity -> fromEntityToDTO(entity, entity.getMenuCategory())) //
                .collect(Collectors.toList());
    }

    public List<FoodItemVariationDTO> getAllFoodItemVariations(String foodItemId) {
        FoodItem foodItem = findFoodItemById(foodItemId);
        List<FoodItemVariation> foodItemVariations = foodItem.getFoodItemVariations();
        return foodItemVariations.stream()
                .map(FoodItemVariationMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<FoodImageDTO> getAllFoodImages(String foodItemId) {
        FoodItem foodItem = findFoodItemById(foodItemId);
        List<FoodImage> foodImages = foodItem.getFoodImages();
        return foodImages.stream()
                .map(FoodImageMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public void createFoodItems(CreateFoodItemDTO createFoodItemDTO) {
        boolean categoryNameCheck = foodItemRepository.existsByFoodItemName(createFoodItemDTO.foodItemName());
        if(categoryNameCheck) throw new DuplicateKeyException(createFoodItemDTO.foodItemName());
        MenuCategory menuCategory = findMenuCategoryById(createFoodItemDTO.menuCategoryId());
        FoodItem foodItem = fromDTOToEntity(createFoodItemDTO, menuCategory);
        menuCategory.getFoodItems().add(foodItem);
        foodItemRepository.save(foodItem);
    }

    public void deleteFoodItem(String foodItemId) throws NotFoundException {
        boolean FoodItemExist = foodItemRepository.existsById(foodItemId);
        if(!FoodItemExist) throw new NotFoundException("Food Item", foodItemId);
        foodItemRepository.deleteById(foodItemId);
    }

    public FoodItemDTO updateFoodItem(String foodItemId, UpdateFoodItemDTO updateFoodItemDTO) {
        FoodItem foodItem = findFoodItemById(foodItemId);
        MenuCategory menuCategory = findMenuCategoryById(updateFoodItemDTO.menuCategoryId());
        foodItem.setMenuCategory(menuCategory);
        foodItem.setFoodItemName(updateFoodItemDTO.foodItemName());
        foodItem.setFoodItemDescription(updateFoodItemDTO.foodItemDescription());
        foodItemRepository.save(foodItem);
        FoodItemDTO FoodItemDTOUpdated = fromEntityToDTO(foodItem, menuCategory);
        return FoodItemDTOUpdated;
    }

    /**
     * searches for all the foodItems based on their foodItem names, that contains the input string provided
     * by the client.
     * Method will look for the foodItems, and convert all foodItem entities into a DTO to be returned back to
     * the client.
     *
     * @param searchInput the string that is inputted and used in our search query to find foodItem names
     *                    that contain the searchInput string.
     * @return the list of foodItems in DTO form, or an empty list.
     */
    public List<FoodItemDTO> searchFoodItems(String searchInput) {
        String wildCardSearchInput = "%" + searchInput + "%";
        Iterable<FoodItem> FoodItemsIterable = foodItemRepository.findByFoodItemNameLike(wildCardSearchInput);
        if(!FoodItemsIterable.iterator().hasNext()) {
            return Collections.emptyList();
        }
        List<FoodItemDTO> FoodItems = StreamSupport.stream(FoodItemsIterable.spliterator(), false)
                .map(entity -> fromEntityToDTO(entity, entity.getMenuCategory()))
                .collect(Collectors.toList());
        return FoodItems;
    }

}
