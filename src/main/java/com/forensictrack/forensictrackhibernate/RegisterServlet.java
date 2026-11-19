
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = SpringUtil.getBean(
                getServletContext(), UserDAO.class
        );
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (fullName == null || email == null
                || username == null || password == null
                || fullName.trim().isEmpty()
                || email.trim().isEmpty()
                || username.trim().isEmpty()
                || password.length() < 8
                || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            request.setAttribute("error",
                    "Please enter valid details. Password must contain at least 8 characters.");

            request.getRequestDispatcher("/register.jsp")
                    .forward(request, response);
            return;
        }

        User user = new User(
                fullName.trim(),
                email.trim(),
                username.trim(),
                password,
                "Investigator"
        );

        boolean registered = userDAO.registerUser(user);

        if (registered) {
            request.setAttribute("success",
                    "Registration successful! You can now log in.");
        } else {
            request.setAttribute("error",
                    "Username or email already exists.");
        }

        request.getRequestDispatcher("/register.jsp")
                .forward(request, response);
    }
}
