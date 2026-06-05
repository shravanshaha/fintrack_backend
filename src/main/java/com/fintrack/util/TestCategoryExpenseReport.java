package com.fintrack.util;

import java.util.Map;

import com.fintrack.dao.TransactionDAO;

public class TestCategoryExpenseReport {

    public static void main(String[] args) {

        TransactionDAO dao =
                new TransactionDAO();

        Map<String, Double> data =
                dao.getCategoryWiseExpense(1);

        for(Map.Entry<String, Double> entry
                : data.entrySet()) {

            System.out.println(
                    entry.getKey()
                    + " : "
                    + entry.getValue()
            );
        }
    }
}