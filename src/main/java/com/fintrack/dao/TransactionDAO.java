package com.fintrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.fintrack.db.DBConnection;
import com.fintrack.model.Transaction;

public class TransactionDAO {

    public boolean addTransaction(
            Transaction transaction) {

        String sql =
                "INSERT INTO transactions "
                + "(user_id, category_id, amount, type, note, date) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    transaction.getUserId()
            );

            ps.setInt(
                    2,
                    transaction.getCategoryId()
            );

            ps.setDouble(
                    3,
                    transaction.getAmount()
            );

            ps.setString(
                    4,
                    transaction.getType()
            );

            ps.setString(
                    5,
                    transaction.getNote()
            );

            ps.setDate(
                    6,
                    transaction.getDate()
            );

            int rows =
                    ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }
    
    public List<Transaction> getTransactionsByUserId(
            int userId) {

        List<Transaction> transactions =
                new ArrayList<>();

        String sql =
                "SELECT * FROM transactions WHERE user_id=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Transaction transaction =
                        new Transaction();

                transaction.setId(
                        rs.getInt("id")
                );

                transaction.setUserId(
                        rs.getInt("user_id")
                );

                transaction.setCategoryId(
                        rs.getInt("category_id")
                );

                transaction.setAmount(
                        rs.getDouble("amount")
                );

                transaction.setType(
                        rs.getString("type")
                );

                transaction.setNote(
                        rs.getString("note")
                );

                transaction.setDate(
                        rs.getDate("date")
                );

                transactions.add(transaction);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return transactions;
    }
    
    public double getTotalIncome(int userId) {

        String sql =
                "SELECT SUM(amount) FROM transactions "
                + "WHERE user_id=? AND type='INCOME'";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getDouble(1);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;
    }
    
    public double getTotalExpense(int userId) {

        String sql =
                "SELECT SUM(amount) FROM transactions "
                + "WHERE user_id=? AND type='EXPENSE'";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return rs.getDouble(1);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;
    }
    
    public double getBalance(int userId) {

        return getTotalIncome(userId)
                - getTotalExpense(userId);
    }
    
    public boolean updateTransaction(
            Transaction transaction) {

        String sql =
                "UPDATE transactions "
                + "SET category_id=?, amount=?, type=?, note=?, date=? "
                + "WHERE id=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    transaction.getCategoryId()
            );

            ps.setDouble(
                    2,
                    transaction.getAmount()
            );

            ps.setString(
                    3,
                    transaction.getType()
            );

            ps.setString(
                    4,
                    transaction.getNote()
            );

            ps.setDate(
                    5,
                    transaction.getDate()
            );

            ps.setInt(
                    6,
                    transaction.getId()
            );

            int rows =
                    ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }
    
    public boolean deleteTransaction(int id) {

        String sql =
                "DELETE FROM transactions WHERE id=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            int rows =
                    ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }
    
    public double getMonthlyIncome(
            int userId,
            int month,
            int year) {

    	String sql =
    	        "SELECT SUM(amount) "
    	        + "FROM transactions "
    	        + "WHERE user_id=? "
    	        + "AND type='INCOME' "
    	        + "AND EXTRACT(MONTH FROM date)=? "
    	        + "AND EXTRACT(YEAR FROM date)=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);
            ps.setInt(2, month);
            ps.setInt(3, year);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    public double getMonthlyExpense(
            int userId,
            int month,
            int year) {

    	String sql =
    	        "SELECT SUM(amount) "
    	        + "FROM transactions "
    	        + "WHERE user_id=? "
    	        + "AND type='EXPENSE' "
    	        + "AND EXTRACT(MONTH FROM date)=? "
    	        + "AND EXTRACT(YEAR FROM date)=?";
        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);
            ps.setInt(2, month);
            ps.setInt(3, year);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    public double getMonthlyBalance(
            int userId,
            int month,
            int year) {

        return getMonthlyIncome(
                userId,
                month,
                year)
                -
                getMonthlyExpense(
                        userId,
                        month,
                        year);
    }
    
    public List<Transaction> getRecentTransactions(
            int userId) {

        List<Transaction> transactions =
                new ArrayList<>();

        String sql =
                "SELECT * FROM transactions "
                + "WHERE user_id=? "
                + "ORDER BY date DESC "
                + "LIMIT 5";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Transaction transaction =
                        new Transaction();

                transaction.setId(
                        rs.getInt("id"));

                transaction.setUserId(
                        rs.getInt("user_id"));

                transaction.setCategoryId(
                        rs.getInt("category_id"));

                transaction.setAmount(
                        rs.getDouble("amount"));

                transaction.setType(
                        rs.getString("type"));

                transaction.setNote(
                        rs.getString("note"));

                transaction.setDate(
                        rs.getDate("date"));

                transactions.add(transaction);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }
    
    public List<Transaction> getTransactionsByDateRange(
            int userId,
            java.sql.Date startDate,
            java.sql.Date endDate) {

        List<Transaction> transactions =
                new ArrayList<>();

        String sql =
                "SELECT * FROM transactions "
                + "WHERE user_id=? "
                + "AND date BETWEEN ? AND ?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);

            ps.setDate(2, startDate);

            ps.setDate(3, endDate);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Transaction transaction =
                        new Transaction();

                transaction.setId(
                        rs.getInt("id"));

                transaction.setUserId(
                        rs.getInt("user_id"));

                transaction.setCategoryId(
                        rs.getInt("category_id"));

                transaction.setAmount(
                        rs.getDouble("amount"));

                transaction.setType(
                        rs.getString("type"));

                transaction.setNote(
                        rs.getString("note"));

                transaction.setDate(
                        rs.getDate("date"));

                transactions.add(transaction);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }
    
    public List<Transaction> searchTransactions(
            int userId,
            String keyword) {

        List<Transaction> transactions =
                new ArrayList<>();

        String sql =
                "SELECT * FROM transactions "
                + "WHERE user_id=? "
                + "AND note LIKE ?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);

            ps.setString(
                    2,
                    "%" + keyword + "%");

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Transaction transaction =
                        new Transaction();

                transaction.setId(
                        rs.getInt("id"));

                transaction.setUserId(
                        rs.getInt("user_id"));

                transaction.setCategoryId(
                        rs.getInt("category_id"));

                transaction.setAmount(
                        rs.getDouble("amount"));

                transaction.setType(
                        rs.getString("type"));

                transaction.setNote(
                        rs.getString("note"));

                transaction.setDate(
                        rs.getDate("date"));

                transactions.add(transaction);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }
    
    
    public Map<String, Double>
    getCategoryWiseExpense(
            int userId) {

        Map<String, Double> data =
                new HashMap<>();

        String sql =
                "SELECT c.name, "
                + "SUM(t.amount) total "
                + "FROM transactions t "
                + "JOIN categories c "
                + "ON t.category_id = c.id "
                + "WHERE t.user_id=? "
                + "AND t.type='EXPENSE' "
                + "GROUP BY c.name";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                data.put(
                        rs.getString("name"),
                        rs.getDouble("total")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}