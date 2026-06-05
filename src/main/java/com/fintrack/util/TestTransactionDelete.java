package com.fintrack.util;

import com.fintrack.dao.TransactionDAO;

public class TestTransactionDelete {

    public static void main(String[] args) {

        TransactionDAO dao =
                new TransactionDAO();

        boolean result =
                dao.deleteTransaction(1);

        if(result) {

            System.out.println(
                    "Transaction Deleted Successfully!"
            );

        } else {

            System.out.println(
                    "Transaction Delete Failed!"
            );

        }
    }
}