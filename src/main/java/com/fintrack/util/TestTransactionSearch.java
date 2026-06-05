package com.fintrack.util;

import java.util.List;

import com.fintrack.dao.TransactionDAO;
import com.fintrack.model.Transaction;

public class TestTransactionSearch {

    public static void main(String[] args) {

        TransactionDAO dao =
                new TransactionDAO();

        List<Transaction> transactions =
                dao.searchTransactions(
                        1,
                        "Salary"
                );

        for(Transaction t : transactions) {

            System.out.println(
                    t.getAmount()
                    + " | "
                    + t.getType()
                    + " | "
                    + t.getNote()
            );
        }
    }
}