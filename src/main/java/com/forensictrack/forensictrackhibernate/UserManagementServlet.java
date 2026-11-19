
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/users")
public class UserManagementServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = SpringUtil.getBean(
                getServletContext(), UserDAO.class
        );
    }

    private boolean isAdmin(HttpSession session) {
        return session != null
                && "Admin".equalsIgnoreCase(
                        (String) session.getAttribute("role")
                );
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (!isAdmin(session)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        request.setAttribute("userList", userDAO.getAllUsers());

        request.getRequestDispatcher("/users.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (!isAdmin(session)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (fullName == null || email == null || username == null
                || password == null || role == null
                || fullName.trim().isEmpty()
                || email.trim().isEmpty()
                || username.trim().isEmpty()
                || password.length() < 8
                || (!role.equals("Admin") && !role.equals("Investigator"))) {

            session.setAttribute("message",
                    "Please enter valid user details. Password must contain at least 8 characters.");

            response.sendRedirect("addUser.jsp");
            return;
        }

        User user = new User(
                fullName.trim(),
                email.trim(),
                username.trim(),
                password,
                role
        );

        boolean success = userDAO.registerUser(user);

        session.setAttribute("message",
                success ? "User added successfully."
                        : "Username or email already exists.");

        response.sendRedirect("users");
    }
}
