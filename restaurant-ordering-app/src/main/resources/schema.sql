CREATE TABLE IF NOT EXISTS menu_categories (
	menu_category_id INT AUTO_INCREMENT,
    menu_category_name VARCHAR(100) UNIQUE NOT NULL,
    CONSTRAINT pk_menu_category PRIMARY KEY (menu_category_id)
);

CREATE TABLE IF NOT EXISTS food_sizes (
	food_size_id INT AUTO_INCREMENT,
    food_size_name VARCHAR(100) UNIQUE NOT NULL,
    CONSTRAINT pk_food_size PRIMARY KEY (food_size_id)
);

CREATE TABLE IF NOT EXISTS food_items (
	food_item_id VARCHAR(36),
	menu_category_id INT NOT NULL,
	food_item_name VARCHAR(500) UNIQUE NOT NULL,
    food_item_description VARCHAR(2000),
    CONSTRAINT pk_food_item PRIMARY KEY (food_item_id),
    CONSTRAINT fk_menu_category FOREIGN KEY (menu_category_id) REFERENCES menu_categories (menu_category_id)
);

CREATE TABLE IF NOT EXISTS food_item_variations (
	food_item_variation_id INT AUTO_INCREMENT,
	food_item_id VARCHAR(36) NOT NULL,
    food_size_id INT,
    food_price DECIMAL(6,2) NOT NULL,
    CONSTRAINT pk_item_variation PRIMARY KEY (food_item_variation_id),
    CONSTRAINT fk_size FOREIGN KEY (food_size_id) REFERENCES food_sizes (food_size_id),
    CONSTRAINT fk_item FOREIGN KEY (food_item_id) REFERENCES food_items (food_item_id)
);

CREATE TABLE IF NOT EXISTS food_images (
	food_image_id INT AUTO_INCREMENT,
    food_item_id VARCHAR(36) NOT NULL,
    image_url VARCHAR(1000),
    CONSTRAINT pk_food_image PRIMARY KEY (food_image_id),
    CONSTRAINT fk_food_item FOREIGN KEY (food_item_id) REFERENCES food_items (food_item_id)
);




-- Users section of schemas
CREATE TABLE IF NOT EXISTS user_roles (
	user_role_id VARCHAR(36),
	user_role_name VARCHAR(100) UNIQUE NOT NULL,
    CONSTRAINT pk_user_role PRIMARY KEY (user_role_id)
);

CREATE TABLE IF NOT EXISTS users (
	user_id VARCHAR(36),
    user_role_id VARCHAR(36) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    user_email VARCHAR(200) UNIQUE NOT NULL,
    user_password VARCHAR(1000) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id),
    CONSTRAINT fk_user_role FOREIGN KEY (user_role_id) REFERENCES user_roles (user_role_id),
    CONSTRAINT min_length_check CHECK (CHAR_LENGTH(user_password) >= 8)
);

CREATE TABLE IF NOT EXISTS user_restaurant_reviews (
	user_restaurant_review_id INT AUTO_INCREMENT,
    user_id VARCHAR(36) NOT NULL,
    user_restaurant_review_title VARCHAR(200) NOT NULL,
    user_restaurant_rating INT NOT NULL,
    user_restaurant_review_description VARCHAR(2000) NOT NULL,
    created_at TIMESTAMP,
    CONSTRAINT pk_user_restaurant_review PRIMARY KEY (user_restaurant_review_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES  users (user_id)
);

CREATE TABLE IF NOT EXISTS user_food_items (
	user_food_id INT AUTO_INCREMENT,
    user_id VARCHAR(36) NOT NULL,
    food_item_id VARCHAR(36) NOT NULL,
    user_food_item_quantity INT NOT NULL,
    CONSTRAINT pk_user_food_cart PRIMARY KEY (user_food_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_food_item_id FOREIGN KEY (food_item_id) REFERENCES food_items (food_item_id)
);





