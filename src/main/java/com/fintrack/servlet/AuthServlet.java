package com.fintrack.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fintrack.dao.UserDAO;
import com.fintrack.model.User;
import jakarta.servlet.http.HttpSession;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
	        HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {

	    String action = request.getParameter("action");

	    if ("register".equals(action)) {

	        String name =
	                request.getParameter("name");

	        String email =
	                request.getParameter("email");

	        String password =
	                request.getParameter("password");

	        User user = new User();

	        user.setName(name);
	        user.setEmail(email);
	        user.setPassword(password);

	        UserDAO dao = new UserDAO();

	        boolean result =
	                dao.registerUser(user);

	        if(result) {

	            response.getWriter()
	                    .println(
	                            "User Registered Successfully!"
	                    );

	        } else {

	            response.getWriter()
	                    .println(
	                            "Registration Failed!"
	                    );

	        }

	    }
	    else if ("login".equals(action)) {

	        String email =
	                request.getParameter("email");

	        String password =
	                request.getParameter("password");

	        UserDAO dao = new UserDAO();

	        User user =
	                dao.loginUser(email, password);

	        if(user != null) {

	            response.setContentType(
	                    "application/json"
	            );

	            response.getWriter()
	                    .write(
	                            "{"
	                            + "\"success\":true,"
	                            + "\"userId\":"
	                            + user.getId()
	                            + ","
	                            + "\"name\":\""
	                            + user.getName()
	                            + "\""
	                            + "}"
	                    );
	        } else {

	            response.getWriter()
	                    .println(
	                            "Invalid Credentials"
	                    );
	        }

	    }
	    else if ("profile".equals(action)) {

	        HttpSession session =
	                request.getSession(false);

	        if(session == null) {

	            response.getWriter()
	                    .println("No Active Session");

	            return;
	        }

	        Object userId =
	                session.getAttribute("userId");

	        Object userName =
	                session.getAttribute("userName");

	        if(userId == null) {

	            response.getWriter()
	                    .println("User Not Logged In");

	        } else {

	            response.getWriter()
	                    .println(
	                            "Logged In User: "
	                            + userName
	                            + " (ID: "
	                            + userId
	                            + ")"
	                    );
	        }

	    }
	    
	    else if ("logout".equals(action)) {

	        HttpSession session =
	                request.getSession(false);

	        if(session != null) {

	            session.invalidate();

	            response.getWriter()
	                    .println(
	                            "Logout Successful!"
	                    );

	        } else {

	            response.getWriter()
	                    .println(
	                            "No Active Session"
	                    );
	        }
	    }
	    
	    else {

	        response.getWriter()
	                .println("Invalid Action");

	    }
	}
}