
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    private boolean isAdmin(HttpSession session) {
        return session != null
                && "Admin".equals(session.getAttribute("role"));
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (!isAdmin(session)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN,
                    "Administrator access required.");
            return;
        }

        try {
            int userId = Integer.parseInt(request.getParameter("userId"));

            User user = userDAO.getUserById(userId);

            if (user == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "User not found.");
                return;
            }

            request.setAttribute("editUser", user);
            request.getRequestDispatcher("editUser.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid user ID.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (!isAdmin(session)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN,
                    "Administrator access required.");
            return;
        }

        try {
            int userId = Integer.parseInt(request.getParameter("userId"));

            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String role = request.getParameter("role");

            User existingUser = userDAO.getUserById(userId);

            if (existingUser == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "User not found.");
                return;
            }

            if (fullName == null || fullName.trim().isEmpty()
                    || email == null || email.trim().isEmpty()
                    || role == null
                    || !email.trim().matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
                    || (!"Admin".equals(role)
                    && !"Investigator".equals(role))) {

                request.setAttribute("error",
                        "Please enter valid user details.");
                request.setAttribute("editUser", existingUser);
                request.getRequestDispatcher("editUser.jsp")
                        .forward(request, response);
                return;
            }

            boolean updated = userDAO.updateUser(
                    userId,
                    fullName.trim(),
                    email.trim(),
                    role
            );

            if (updated) {
                session.setAttribute("userMessage",
                        "User updated successfully.");
            } else {
                session.setAttribute("userMessage",
                        "Update failed. Email may already be in use.");
            }

            response.sendRedirect("users");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid user ID.");
        }
    }
}
