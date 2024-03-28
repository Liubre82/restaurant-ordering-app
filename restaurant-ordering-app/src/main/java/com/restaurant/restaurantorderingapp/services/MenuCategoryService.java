package com.restaurant.restaurantorderingapp.services;

import com.restaurant.restaurantorderingapp.dto.foodItemsDto.FoodItemDTO;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.CreateMenuCategoryDTO;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.MenuCategoryDTO;
import com.restaurant.restaurantorderingapp.dto.menuCategoriesDto.UpdateMenuCategoryDTO;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.DuplicateKeyException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.EmptyDataTableException;
import com.restaurant.restaurantorderingapp.exceptions.customExceptions.NotFoundException;
import com.restaurant.restaurantorderingapp.models.food.FoodItem;
import com.restaurant.restaurantorderingapp.models.food.MenuCategory;
import com.restaurant.restaurantorderingapp.repositories.MenuCategoryRepository;
import com.restaurant.restaurantorderingapp.utils.mappers.FoodItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.restaurant.restaurantorderingapp.utils.mappers.MenuCategoryMapper.fromDTOToEntity;
import static com.restaurant.restaurantorderingapp.utils.mappers.MenuCategoryMapper.fromEntityToDTO;

@Service
public class MenuCategoryService {

    private final MenuCategoryRepository menuCategoryRepository;

    @Autowired
    public MenuCategoryService(MenuCategoryRepository menuCategoryRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
    }

    public MenuCategory findMenuCategoryById(Long menuCategoryId) {
        return menuCategoryRepository.findById(menuCategoryId)
                .orElseThrow(() -> new NotFoundException("Menu Category", menuCategoryId));
    }

    public MenuCategoryDTO getMenuCategoryById(Long menuCategoryId) {
        MenuCategory menuCategory = findMenuCategoryById(menuCategoryId);
        MenuCategoryDTO menuCategoryDTO = fromEntityToDTO(menuCategory);
        return  menuCategoryDTO;
    }

    /* Retrieve all entities from menu_categories table then pass it into a
     * stream to be converted into DTOs and return it in a List*/
    public List<MenuCategoryDTO> getAllMenuCategories() {
        String entityName = "menu categories";
        List<MenuCategory> menuCategories = (List<MenuCategory>) menuCategoryRepository.findAll();
        if(menuCategories.isEmpty()) throw new EmptyDataTableException(entityName);
        return menuCategories.stream()
                .map(entity -> fromEntityToDTO(entity))
                .collect(Collectors.toList());
    }

    public List<FoodItemDTO> getAllFoodItemsByMenuCategoryId(Long menuCategoryId) {
        MenuCategory menuCategory = findMenuCategoryById(menuCategoryId);
        List<FoodItem> foodItems = menuCategory.getFoodItems();
        return foodItems.stream()
                .map(entity -> FoodItemMapper.fromEntityToDTO(entity, menuCategory))
                .collect(Collectors.toList());
    }

    public void createMenuCategories(CreateMenuCategoryDTO createMenuCategoryDTO) {
        boolean categoryNameCheck = menuCategoryRepository.existsByMenuCategoryName(createMenuCategoryDTO.menuCategoryName());
        if(categoryNameCheck) throw new DuplicateKeyException(createMenuCategoryDTO.menuCategoryName());
        MenuCategory menuCategory = fromDTOToEntity(createMenuCategoryDTO);
        menuCategoryRepository.save(menuCategory);
    }

    public void deleteMenuCategory(Long menuCategoryId) throws NotFoundException {
        boolean menuCategoryExist = menuCategoryRepository.existsById(menuCategoryId);
        if(!menuCategoryExist) throw new NotFoundException("Menu Category", menuCategoryId);
        menuCategoryRepository.deleteById(menuCategoryId);
    }

    public MenuCategoryDTO updateMenuCategory(Long menuCategoryId, UpdateMenuCategoryDTO updateMenuCategoryDTO) {
        MenuCategory menuCategory = findMenuCategoryById(menuCategoryId);
        menuCategory.setMenuCategoryName(updateMenuCategoryDTO.menuCategoryName());
        menuCategoryRepository.save(menuCategory);
        MenuCategoryDTO menuCategoryDTOUpdated = fromEntityToDTO(menuCategory);
        return menuCategoryDTOUpdated;
    }

    public List<MenuCategoryDTO> searchMenuCategories(String searchInput) {
        String wildCardSearchInput = "%" + searchInput + "%";
        Iterable<MenuCategory> menuCategoriesIterable = menuCategoryRepository.findByMenuCategoryNameLike(wildCardSearchInput);
        if(!menuCategoriesIterable.iterator().hasNext()) {
            return Collections.emptyList();
        }
        List<MenuCategoryDTO> menuCategories = StreamSupport.stream(menuCategoriesIterable.spliterator(), false)
                .map(entity -> fromEntityToDTO(entity))
                .collect(Collectors.toList());
        return menuCategories;
    }
}