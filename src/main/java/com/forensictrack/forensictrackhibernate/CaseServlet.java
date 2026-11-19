
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.Case;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cases")
public class CaseServlet extends HttpServlet {

    private CaseDAO caseDAO;

    @Override
    public void init() throws ServletException {
        caseDAO = SpringUtil.getBean(
                getServletContext(),
                CaseDAO.class
        );
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

        request.setAttribute("cases", caseDAO.getAllCases());

        request.getRequestDispatcher("/cases.jsp")
                .forward(request, response);
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

        try {
            String caseTitle = request.getParameter("caseTitle");
            String caseType = request.getParameter("caseType");
            String description = request.getParameter("description");
            String priority = request.getParameter("priority");
            String status = request.getParameter("status");

            if (caseTitle == null || caseTitle.trim().isEmpty()
                    || caseType == null || caseType.trim().isEmpty()
                    || description == null || description.trim().isEmpty()
                    || priority == null || priority.trim().isEmpty()
                    || status == null || status.trim().isEmpty()) {

                response.sendRedirect("addCase.jsp");
                return;
            }

            int userId = (Integer) session.getAttribute("userId");

            Case caseObj = new Case(
                    caseTitle.trim(),
                    caseType.trim(),
                    description.trim(),
                    priority.trim(),
                    status.trim(),
                    java.time.LocalDate.now(),
                    userId
            );

            caseDAO.createCase(caseObj);

            response.sendRedirect("cases");

        } catch (Exception e) {
            e.printStackTrace();

            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Unable to create the case."
            );
        }
    }
}
