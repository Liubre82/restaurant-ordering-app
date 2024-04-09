package com.restaurant.restaurantorderingapp.models.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userRoleId;

    @Column(name = "user_role_name", unique = true)
    private String userRoleName;

    public String getUserRoleId() {
        return userRoleId;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }
}
