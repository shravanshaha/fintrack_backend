package com.fintrack.util;

import java.util.List;

import com.fintrack.dao.TransactionDAO;
import com.fintrack.model.Transaction;

public class TestRecentTransactions {

    public static void main(String[] args) {

        TransactionDAO dao =
                new TransactionDAO();

        List<Transaction> transactions =
                dao.getRecentTransactions(1);

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