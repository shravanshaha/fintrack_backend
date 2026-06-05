package com.fintrack.util;

import com.fintrack.dao.UserDAO;
import com.fintrack.model.User;

public class TestLogin {

    public static void main(String[] args) {

        UserDAO dao = new UserDAO();

        User user = dao.loginUser(
                "shravan@test.com",
                "password123"
        );

        if(user != null) {

            System.out.println("Login Successful!");

            System.out.println(
                    "Welcome " + user.getName()
            );

        } else {

            System.out.println("Invalid Credentials");

        }
    }
}