package com.fintrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.mindrot.jbcrypt.BCrypt;

import com.fintrack.db.DBConnection;
import com.fintrack.model.User;
import java.sql.ResultSet;
import com.fintrack.util.PasswordUtil;

public class UserDAO {

    public boolean registerUser(User user) {

        String sql =
                "INSERT INTO users(name,email,password) VALUES(?,?,?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

        	String hashedPassword =
        	        PasswordUtil.hashPassword(
        	                user.getPassword()
        	        );

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, hashedPassword);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }
    
    
    
    public User loginUser(String email, String password) {

        String sql =
                "SELECT * FROM users WHERE email = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String storedHash =
                        rs.getString("password");

                if (PasswordUtil.checkPassword(
                        password,
                        storedHash)) {

                    User user = new User();

                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(storedHash);

                    return user;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }
}