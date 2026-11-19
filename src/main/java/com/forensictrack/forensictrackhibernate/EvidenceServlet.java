
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.Evidence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/evidence")
public class EvidenceServlet extends HttpServlet {

    private EvidenceDAO evidenceDAO;
    private CaseDAO caseDAO;

    @Override
    public void init() throws ServletException {

        evidenceDAO = SpringUtil.getBean(
                getServletContext(),
                EvidenceDAO.class
        );

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

        request.setAttribute(
                "evidenceList",
                evidenceDAO.getAllEvidence()
        );

        request.getRequestDispatcher("/evidence.jsp")
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
            String caseIdValue = request.getParameter("caseId");
            String evidenceType = request.getParameter("evidenceType");
            String description = request.getParameter("description");
            String filePath = request.getParameter("filePath");

            if (caseIdValue == null || evidenceType == null
                    || description == null || filePath == null
                    || evidenceType.trim().isEmpty()
                    || description.trim().isEmpty()
                    || filePath.trim().isEmpty()) {

                request.setAttribute(
                        "error",
                        "Please fill in all fields."
                );

                request.getRequestDispatcher("/addEvidence.jsp")
                        .forward(request, response);
                return;
            }

            int caseId = Integer.parseInt(caseIdValue);

            if (caseId <= 0) {
                request.setAttribute(
                        "error",
                        "Please enter a valid Case ID."
                );

                request.getRequestDispatcher("/addEvidence.jsp")
                        .forward(request, response);
                return;
            }

            if (caseDAO.getCaseById(caseId) == null) {
                request.setAttribute(
                        "error",
                        "The entered Case ID does not exist."
                );

                request.getRequestDispatcher("/addEvidence.jsp")
                        .forward(request, response);
                return;
            }

            Evidence evidence = new Evidence(
                    caseId,
                    evidenceType.trim(),
                    description.trim(),
                    filePath.trim(),
                    LocalDate.now()
            );

            evidenceDAO.createEvidence(evidence);

            response.sendRedirect("evidence");

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Please enter a valid Case ID."
            );

            request.getRequestDispatcher("/addEvidence.jsp")
                    .forward(request, response);
        }
    }
}

