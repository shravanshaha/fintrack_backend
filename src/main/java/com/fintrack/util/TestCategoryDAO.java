package com.fintrack.util;

import com.fintrack.dao.CategoryDAO;
import com.fintrack.model.Category;

public class TestCategoryDAO {

    public static void main(String[] args) {

        Category category =
                new Category();

        category.setUserId(1);

        category.setName("Food");

        category.setType("EXPENSE");

        CategoryDAO dao =
                new CategoryDAO();

        boolean result =
                dao.addCategory(category);

        if(result) {

            System.out.println(
                    "Category Added Successfully!"
            );

        } else {

            System.out.println(
                    "Failed To Add Category!"
            );

        }
    }
}