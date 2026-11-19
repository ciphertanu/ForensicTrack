
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null
                || username.trim().isEmpty()
                || password.isEmpty()) {

            request.setAttribute("error",
                    "Please enter your username and password.");

            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
            return;
        }

        User user = userDAO.loginUser(username.trim(), password);

        if (user != null) {

            HttpSession oldSession = request.getSession(false);

            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession session = request.getSession(true);

            session.setAttribute("userId", user.getUserId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("fullName", user.getFullName());
            session.setAttribute("role", user.getRole());

            response.sendRedirect("dashboard.jsp");

        } else {
            request.setAttribute("error",
                    "Invalid username or password.");

            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
        }
    }
}
