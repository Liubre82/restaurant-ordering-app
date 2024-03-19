package com.restaurant.restaurantorderingapp.models;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuCategoryTestEntity {

    private Long menuCategoryId;

    private String menuCategoryName;

    public static class MenuCategoryTestEntityBuilder {

        private Long menuCategoryId;
        private String menuCategoryName;

        public MenuCategoryTestEntityBuilder() {
        }

        public MenuCategoryTestEntityBuilder setMenuCategoryId(Long menuCategoryId) {
            this.menuCategoryId = menuCategoryId;
            return this;
        }

        public MenuCategoryTestEntityBuilder setMenuCategoryName(String menuCategoryName) {
            this.menuCategoryName = menuCategoryName;
            return this;
        }

        public MenuCategoryTestEntity build() {
            MenuCategoryTestEntity menuCategoryTestEntity = new MenuCategoryTestEntity();
            menuCategoryTestEntity.setMenuCategoryId(this.menuCategoryId);
            menuCategoryTestEntity.setMenuCategoryName(this.menuCategoryName);
            return menuCategoryTestEntity;
        }
    }


}
