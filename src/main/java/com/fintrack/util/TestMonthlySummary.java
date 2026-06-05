package com.fintrack.util;

import com.fintrack.dao.TransactionDAO;

public class TestMonthlySummary {

    public static void main(String[] args) {

        TransactionDAO dao =
                new TransactionDAO();

        int userId = 1;

        int month = 6;

        int year = 2026;

        double income =
                dao.getMonthlyIncome(
                        userId,
                        month,
                        year);

        double expense =
                dao.getMonthlyExpense(
                        userId,
                        month,
                        year);

        double balance =
                dao.getMonthlyBalance(
                        userId,
                        month,
                        year);

        System.out.println(
                "Monthly Income : "
                + income);

        System.out.println(
                "Monthly Expense : "
                + expense);

        System.out.println(
                "Monthly Balance : "
                + balance);
    }
}