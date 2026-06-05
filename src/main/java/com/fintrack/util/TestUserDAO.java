package com.fintrack.util;

import com.fintrack.dao.UserDAO;
import com.fintrack.model.User;

public class TestUserDAO {

    public static void main(String[] args) {

        User user = new User();

        user.setName("Shravan");
        user.setEmail("shravan2@test.com");
        user.setPassword("password123");

        UserDAO dao = new UserDAO();

        boolean result = dao.registerUser(user);

        if(result) {
            System.out.println("User Registered Successfully!");
        } else {
            System.out.println("Registration Failed!");
        }
    }
}