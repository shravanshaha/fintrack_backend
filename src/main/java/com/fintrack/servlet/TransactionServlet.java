package com.fintrack.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.fintrack.dao.TransactionDAO;
import com.fintrack.model.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {

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

        if("create".equals(action)) {

            Transaction transaction =
                    new Transaction();

            transaction.setUserId(userId);

            transaction.setCategoryId(
                    Integer.parseInt(
                            request.getParameter(
                                    "categoryId"
                            )
                    )
            );

            transaction.setAmount(
                    Double.parseDouble(
                            request.getParameter(
                                    "amount"
                            )
                    )
            );

            transaction.setType(
                    request.getParameter(
                            "type"
                    )
            );

            transaction.setNote(
                    request.getParameter(
                            "note"
                    )
            );

            transaction.setDate(
                    Date.valueOf(
                            request.getParameter(
                                    "date"
                            )
                    )
            );

            boolean result =
                    dao.addTransaction(
                            transaction
                    );

            response.getWriter()
                    .println(
                            result
                            ? "Transaction Created"
                            : "Transaction Creation Failed"
                    );
        }

        else if("list".equals(action)) {

            List<Transaction> transactions =
                    dao.getTransactionsByUserId(
                            userId
                    );

            for(Transaction t
                    : transactions) {

                response.getWriter()
                        .println(
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

        else if("update".equals(action)) {

            Transaction transaction =
                    new Transaction();

            transaction.setId(
                    Integer.parseInt(
                            request.getParameter(
                                    "id"
                            )
                    )
            );

            transaction.setCategoryId(
                    Integer.parseInt(
                            request.getParameter(
                                    "categoryId"
                            )
                    )
            );

            transaction.setAmount(
                    Double.parseDouble(
                            request.getParameter(
                                    "amount"
                            )
                    )
            );

            transaction.setType(
                    request.getParameter(
                            "type"
                    )
            );

            transaction.setNote(
                    request.getParameter(
                            "note"
                    )
            );

            transaction.setDate(
                    Date.valueOf(
                            request.getParameter(
                                    "date"
                            )
                    )
            );

            boolean result =
                    dao.updateTransaction(
                            transaction
                    );

            response.getWriter()
                    .println(
                            result
                            ? "Transaction Updated"
                            : "Transaction Update Failed"
                    );
        }

        else if("delete".equals(action)) {

            int id =
                    Integer.parseInt(
                            request.getParameter(
                                    "id"
                            )
                    );

            boolean result =
                    dao.deleteTransaction(id);

            response.getWriter()
                    .println(
                            result
                            ? "Transaction Deleted"
                            : "Transaction Delete Failed"
                    );
        }

        else if("search".equals(action)) {

            String keyword =
                    request.getParameter(
                            "keyword"
                    );

            List<Transaction> transactions =
                    dao.searchTransactions(
                            userId,
                            keyword
                    );

            for(Transaction t
                    : transactions) {

                response.getWriter()
                        .println(
                                t.getAmount()
                                + " | "
                                + t.getType()
                                + " | "
                                + t.getNote()
                        );
            }
        }

        else if("daterange".equals(action)) {

            Date startDate =
                    Date.valueOf(
                            request.getParameter(
                                    "start"
                            )
                    );

            Date endDate =
                    Date.valueOf(
                            request.getParameter(
                                    "end"
                            )
                    );

            List<Transaction> transactions =
                    dao.getTransactionsByDateRange(
                            userId,
                            startDate,
                            endDate
                    );

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

        else {

            response.getWriter()
                    .println(
                            "Invalid Transaction Action"
                    );
        }
    }
}