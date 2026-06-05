package com.fintrack.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fintrack.dao.TransactionDAO;
import com.fintrack.model.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    	int userId =
    	        Integer.parseInt(
    	                request.getParameter(
    	                        "userId"
    	                )
    	        );

        String action =
                request.getParameter("action");

        TransactionDAO dao =
                new TransactionDAO();

        if("summary".equals(action)) {

        	
            double income =
                    dao.getTotalIncome(userId);

            double expense =
                    dao.getTotalExpense(userId);

            double balance =
                    dao.getBalance(userId);

            response.getWriter()
                    .println(
                            "Income : "
                            + income
                    );

            response.getWriter()
                    .println(
                            "Expense : "
                            + expense
                    );

            response.getWriter()
                    .println(
                            "Balance : "
                            + balance
                    );
        }

        else if("recent".equals(action)) {

            List<Transaction> transactions =
                    dao.getRecentTransactions(
                            userId);

            for(Transaction t
                    : transactions) {

                response.getWriter()
                        .println(
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

        else if("categoryReport".equals(action)) {

            Map<String, Double> data =
                    dao.getCategoryWiseExpense(
                            userId);

            for(Map.Entry<String, Double> entry
                    : data.entrySet()) {

                response.getWriter()
                        .println(
                                entry.getKey()
                                + " : "
                                + entry.getValue()
                        );
            }
        }

        else {

            response.getWriter()
                    .println(
                            "Invalid Dashboard Action"
                    );
        }
    }
}