package com.fintrack.util;

import com.fintrack.dao.TransactionDAO;

public class TestDashboard {

    public static void main(String[] args) {

        TransactionDAO dao =
                new TransactionDAO();

        double income =
                dao.getTotalIncome(1);

        double expense =
                dao.getTotalExpense(1);

        double balance =
                dao.getBalance(1);

        System.out.println(
                "Total Income : " + income
        );

        System.out.println(
                "Total Expense : " + expense
        );

        System.out.println(
                "Balance : " + balance
        );
    }
}