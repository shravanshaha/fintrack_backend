package com.fintrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fintrack.db.DBConnection;
import com.fintrack.model.Category;

public class CategoryDAO {

    public boolean addCategory(Category category) {

        String sql =
                "INSERT INTO categories(user_id,name,type) VALUES(?,?,?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    category.getUserId()
            );

            ps.setString(
                    2,
                    category.getName()
            );

            ps.setString(
                    3,
                    category.getType()
            );

            int rows =
                    ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }
    
    public List<Category> getCategoriesByUserId(
            int userId) {

        List<Category> categories =
                new ArrayList<>();

        String sql =
                "SELECT * FROM categories WHERE user_id=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Category category =
                        new Category();

                category.setId(
                        rs.getInt("id")
                );

                category.setUserId(
                        rs.getInt("user_id")
                );

                category.setName(
                        rs.getString("name")
                );

                category.setType(
                        rs.getString("type")
                );

                categories.add(category);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return categories;
    }
    
    public boolean updateCategory(Category category) {

        String sql =
                "UPDATE categories SET name=?, type=? WHERE id=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    category.getName()
            );

            ps.setString(
                    2,
                    category.getType()
            );

            ps.setInt(
                    3,
                    category.getId()
            );

            int rows =
                    ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }
    
    public boolean deleteCategory(int id) {

        String sql =
                "DELETE FROM categories WHERE id=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            int rows =
                    ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }
}