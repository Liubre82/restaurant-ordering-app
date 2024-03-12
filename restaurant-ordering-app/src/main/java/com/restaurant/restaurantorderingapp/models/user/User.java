package com.restaurant.restaurantorderingapp.models.user;

import jakarta.persistence.*;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @OneToOne(targetEntity = UserRole.class)
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;

    private String userName;

    private String userEmail;

    private String userPassword;

}
