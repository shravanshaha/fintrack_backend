package com.fintrack.util;

import com.fintrack.dao.CategoryDAO;

public class TestCategoryDelete {

    public static void main(String[] args) {

        CategoryDAO dao =
                new CategoryDAO();

        boolean result =
                dao.deleteCategory(1);

        if(result) {

            System.out.println(
                    "Category Deleted Successfully!"
            );

        } else {

            System.out.println(
                    "Category Delete Failed!"
            );

        }
    }
}