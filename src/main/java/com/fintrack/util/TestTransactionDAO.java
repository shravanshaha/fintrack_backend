package com.fintrack.util;

import java.sql.Date;

import com.fintrack.dao.TransactionDAO;
import com.fintrack.model.Transaction;

public class TestTransactionDAO {

    public static void main(String[] args) {

        Transaction transaction =
                new Transaction();

        transaction.setUserId(1);

        transaction.setCategoryId(2);

        transaction.setAmount(20000);

        transaction.setType("EXPENSE");

        transaction.setNote(
                "Fees"
        );

        transaction.setDate(
                Date.valueOf("2026-06-02")
        );

        TransactionDAO dao =
                new TransactionDAO();

        boolean result =
                dao.addTransaction(
                        transaction
                );

        if(result) {

            System.out.println(
                    "Transaction Added Successfully!"
            );

        } else {

            System.out.println(
                    "Transaction Add Failed!"
            );

        }
    }
}