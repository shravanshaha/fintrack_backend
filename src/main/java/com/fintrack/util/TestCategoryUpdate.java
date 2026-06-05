package com.fintrack.util;

import com.fintrack.dao.CategoryDAO;
import com.fintrack.model.Category;

public class TestCategoryUpdate {

    public static void main(String[] args) {

        Category category =
                new Category();

        category.setId(1);

        category.setName("Dining Out");

        category.setType("EXPENSE");

        CategoryDAO dao =
                new CategoryDAO();

        boolean result =
                dao.updateCategory(category);

        if(result) {

            System.out.println(
                    "Category Updated Successfully!"
            );

        } else {

            System.out.println(
                    "Category Update Failed!"
            );

        }
    }
}