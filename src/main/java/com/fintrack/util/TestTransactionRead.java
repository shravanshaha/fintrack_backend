package com.fintrack.util;

import java.util.List;

import com.fintrack.dao.TransactionDAO;
import com.fintrack.model.Transaction;

public class TestTransactionRead {

    public static void main(String[] args) {

        TransactionDAO dao =
                new TransactionDAO();

        List<Transaction> transactions =
                dao.getTransactionsByUserId(1);

        for(Transaction t : transactions) {

            System.out.println(
                    t.getId()
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