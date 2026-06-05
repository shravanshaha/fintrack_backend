package com.fintrack.util;

import java.sql.Date;

import com.fintrack.dao.TransactionDAO;
import com.fintrack.model.Transaction;

public class TestTransactionUpdate {

    public static void main(String[] args) {

        Transaction transaction =
                new Transaction();

        transaction.setId(1);

        transaction.setCategoryId(2);

        transaction.setAmount(600);

        transaction.setType("EXPENSE");

        transaction.setNote(
                "Dinner with friends"
        );

        transaction.setDate(
                Date.valueOf("2026-06-02")
        );

        TransactionDAO dao =
                new TransactionDAO();

        boolean result =
                dao.updateTransaction(
                        transaction
                );

        if(result) {

            System.out.println(
                    "Transaction Updated Successfully!"
            );

        } else {

            System.out.println(
                    "Transaction Update Failed!"
            );

        }
    }
}