package com.restaurant.restaurantorderingapp.models.user;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_restaurant_reviews")
public class UserRestaurantReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantReviewId;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    private String userRestaurantReviewId;

    private Long userRestaurantRating;

    private String userRestaurantReviewDescription;

    private Timestamp createdAt;

    public Long getRestaurantReviewId() {
        return restaurantReviewId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserRestaurantReviewId() {
        return userRestaurantReviewId;
    }

    public void setUserRestaurantReviewId(String userRestaurantReviewId) {
        this.userRestaurantReviewId = userRestaurantReviewId;
    }

    public Long getUserRestaurantRating() {
        return userRestaurantRating;
    }

    public void setUserRestaurantRating(Long userRestaurantRating) {
        this.userRestaurantRating = userRestaurantRating;
    }

    public String getUserRestaurantReviewDescription() {
        return userRestaurantReviewDescription;
    }

    public void setUserRestaurantReviewDescription(String userRestaurantReviewDescription) {
        this.userRestaurantReviewDescription = userRestaurantReviewDescription;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
