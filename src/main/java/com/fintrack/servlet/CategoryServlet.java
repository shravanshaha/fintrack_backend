package com.fintrack.servlet;

import java.io.IOException;
import java.util.List;

import com.fintrack.dao.CategoryDAO;
import com.fintrack.model.Category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null ||
                session.getAttribute("userId") == null) {

            response.getWriter()
                    .println("Please Login First");

            return;
        }

        int userId =
                (Integer) session.getAttribute(
                        "userId");

        String action =
                request.getParameter("action");

        CategoryDAO dao =
                new CategoryDAO();

        if("create".equals(action)) {

            String name =
                    request.getParameter("name");

            String type =
                    request.getParameter("type");

            Category category =
                    new Category();

            category.setUserId(userId);
            category.setName(name);
            category.setType(type);

            boolean result =
                    dao.addCategory(category);

            response.getWriter()
                    .println(
                            result
                            ? "Category Created"
                            : "Category Creation Failed"
                    );
        }

        else if("list".equals(action)) {

            List<Category> categories =
                    dao.getCategoriesByUserId(
                            userId);

            for(Category c : categories) {

                response.getWriter()
                        .println(
                                c.getId()
                                + " | "
                                + c.getName()
                                + " | "
                                + c.getType()
                        );
            }
        }

        else if("update".equals(action)) {

            int id =
                    Integer.parseInt(
                            request.getParameter("id")
                    );

            String name =
                    request.getParameter("name");

            String type =
                    request.getParameter("type");

            Category category =
                    new Category();

            category.setId(id);
            category.setName(name);
            category.setType(type);

            boolean result =
                    dao.updateCategory(category);

            response.getWriter()
                    .println(
                            result
                            ? "Category Updated"
                            : "Category Update Failed"
                    );
        }

        else if("delete".equals(action)) {

            int id =
                    Integer.parseInt(
                            request.getParameter("id")
                    );

            boolean result =
                    dao.deleteCategory(id);

            response.getWriter()
                    .println(
                            result
                            ? "Category Deleted"
                            : "Category Delete Failed"
                    );
        }

        else {

            response.getWriter()
                    .println(
                            "Invalid Category Action"
                    );
        }
    }
}