package com.restaurant.restaurantorderingapp.repositories.foodRepositories;

import com.restaurant.restaurantorderingapp.models.food.MenuCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MenuCategoryRepository extends CrudRepository<MenuCategory, Long> {

    boolean existsByMenuCategoryName(String menuCategoryName);

    @Query("SELECT mc FROM MenuCategory mc WHERE LOWER(mc.menuCategoryName) LIKE LOWER(:searchInput)")
    Iterable<MenuCategory> findByMenuCategoryNameLike(String searchInput);
}
