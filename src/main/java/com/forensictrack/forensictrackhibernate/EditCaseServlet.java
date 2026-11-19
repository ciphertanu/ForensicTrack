
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.Case;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/editCase")
public class EditCaseServlet extends HttpServlet {

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

        try {
            int caseId = Integer.parseInt(
                    request.getParameter("id")
            );

            Case caseObj = caseDAO.getCaseById(caseId);

            if (caseObj == null) {
                response.sendRedirect("cases");
                return;
            }

            request.setAttribute("caseObj", caseObj);

            request.getRequestDispatcher("/editCase.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("cases");
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

        try {
            int caseId = Integer.parseInt(
                    request.getParameter("caseId")
            );

            String priority = request.getParameter("priority");
            String status = request.getParameter("status");

            if (priority == null || status == null
                    || priority.trim().isEmpty()
                    || status.trim().isEmpty()) {

                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Priority and status are required."
                );
                return;
            }

            Case existingCase = caseDAO.getCaseById(caseId);

            if (existingCase == null) {
                response.sendRedirect("cases");
                return;
            }

            caseDAO.updateCase(
                    caseId,
                    priority.trim(),
                    status.trim()
            );

            response.sendRedirect("cases");

        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid case ID."
            );
        }
    }
}
