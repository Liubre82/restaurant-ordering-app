package com.restaurant.restaurantorderingapp.utils.auth;

public class UserIdCheck {

    private UserIdCheck(){}

    public static boolean checkStringId(String id, String idCheck) {
        return id.equals(idCheck);
    }

    public static boolean checkLongId(Long id, Long idCheck) {
        return id == idCheck;
    }
}
