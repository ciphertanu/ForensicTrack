
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (!isAdmin(session)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        try {
            int userId = Integer.parseInt(
                    request.getParameter("userId")
            );

            int currentUserId =
                    (Integer) session.getAttribute("userId");

            if (userId == currentUserId) {
                session.setAttribute("message",
                        "You cannot delete your own account.");
                response.sendRedirect("users");
                return;
            }

            User user = userDAO.getUserById(userId);

            if (user == null) {
                session.setAttribute("message", "User not found.");
                response.sendRedirect("users");
                return;
            }

            boolean deleted = userDAO.deleteUser(userId);

            session.setAttribute("message",
                    deleted ? "User deleted successfully."
                            : "User cannot be deleted because related cases or notes exist.");

            response.sendRedirect("users");

        } catch (NumberFormatException e) {
            response.sendRedirect("users");
        }
    }
}
