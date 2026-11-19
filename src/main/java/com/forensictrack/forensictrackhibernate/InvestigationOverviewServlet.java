
package com.forensictrack.forensictrackhibernate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/overview")
public class InvestigationOverviewServlet extends HttpServlet {

    private InvestigationOverviewDAO overviewDAO;

    // SPRING DEPENDENCY INJECTION
    @Override
    public void init() throws ServletException {

        overviewDAO = SpringUtil.getBean(
                getServletContext(),
                InvestigationOverviewDAO.class
        );
    }

    // DISPLAY INVESTIGATION OVERVIEW
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        request.setAttribute(
                "totalCases",
                overviewDAO.getTotalCases()
        );

        request.setAttribute(
                "openCases",
                overviewDAO.getOpenCases()
        );

        request.setAttribute(
                "totalEvidence",
                overviewDAO.getTotalEvidence()
        );

        request.setAttribute(
                "totalCustodyRecords",
                overviewDAO.getTotalCustodyRecords()
        );

        request.setAttribute(
                "casesByStatus",
                overviewDAO.getCasesByStatus()
        );

        request.setAttribute(
                "casesByPriority",
                overviewDAO.getCasesByPriority()
        );

        request.getRequestDispatcher("investigationOverview.jsp")
                .forward(request, response);
    }
}
