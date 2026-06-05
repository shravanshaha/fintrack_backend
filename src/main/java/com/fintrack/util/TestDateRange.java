package com.fintrack.util;

import java.sql.Date;
import java.util.List;

import com.fintrack.dao.TransactionDAO;
import com.fintrack.model.Transaction;

public class TestDateRange {

    public static void main(String[] args) {

        TransactionDAO dao =
                new TransactionDAO();

        List<Transaction> transactions =
                dao.getTransactionsByDateRange(
                        1,
                        Date.valueOf("2026-06-01"),
                        Date.valueOf("2026-06-30")
                );

        for(Transaction t : transactions) {

            System.out.println(
                    t.getDate()
                    + " | "
                    + t.getAmount()
                    + " | "
                    + t.getType()
                    + " | "
                    + t.getNote()
            );
        }
    }
}