package com.fintrack.util;

import java.util.List;

import com.fintrack.dao.CategoryDAO;
import com.fintrack.model.Category;

public class TestCategoryRead {

    public static void main(String[] args) {

        CategoryDAO dao =
                new CategoryDAO();

        List<Category> categories =
                dao.getCategoriesByUserId(1);

        for(Category category : categories) {

            System.out.println(
                    category.getId()
                    + " | "
                    + category.getName()
                    + " | "
                    + category.getType()
            );
        }
    }
}