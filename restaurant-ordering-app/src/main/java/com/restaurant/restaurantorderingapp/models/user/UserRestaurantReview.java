package com.restaurant.restaurantorderingapp.models.user;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_restaurant_reviews")
public class UserRestaurantReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRestaurantReviewId;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_restaurant_review_title")
    private String userRestaurantReviewTitle;

    @Column(name = "user_restaurant_rating")
    private BigDecimal userRestaurantRating;

    @Column(name = "user_restaurant_review_description")
    private String userRestaurantReviewDescription;

    @UpdateTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Long getUserRestaurantReviewId() {
        return userRestaurantReviewId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserRestaurantReviewTitle() {
        return userRestaurantReviewTitle;
    }

    public void setUserRestaurantReviewTitle(String userRestaurantReviewTitle) {
        this.userRestaurantReviewTitle = userRestaurantReviewTitle;
    }

    public BigDecimal getUserRestaurantRating() {
        return userRestaurantRating;
    }

    public void setUserRestaurantRating(BigDecimal userRestaurantRating) {
        this.userRestaurantRating = userRestaurantRating;
    }

    public String getUserRestaurantReviewDescription() {
        return userRestaurantReviewDescription;
    }

    public void setUserRestaurantReviewDescription(String userRestaurantReviewDescription) {
        this.userRestaurantReviewDescription = userRestaurantReviewDescription;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
