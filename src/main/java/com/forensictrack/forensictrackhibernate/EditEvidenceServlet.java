
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.Evidence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/editEvidence")
public class EditEvidenceServlet extends HttpServlet {

    private EvidenceDAO evidenceDAO;

    @Override
    public void init() throws ServletException {
        evidenceDAO = SpringUtil.getBean(
                getServletContext(),
                EvidenceDAO.class
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
            int evidenceId = Integer.parseInt(
                    request.getParameter("id")
            );

            Evidence evidence = evidenceDAO.getEvidenceById(evidenceId);

            if (evidence == null) {
                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Evidence record not found."
                );
                return;
            }

            request.setAttribute("evidence", evidence);

            request.getRequestDispatcher("/editEvidence.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid evidence ID."
            );
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
            int evidenceId = Integer.parseInt(
                    request.getParameter("evidenceId")
            );

            String evidenceType = request.getParameter("evidenceType");
            String description = request.getParameter("description");
            String filePath = request.getParameter("filePath");

            if (evidenceType == null || description == null
                    || filePath == null
                    || evidenceType.trim().isEmpty()
                    || description.trim().isEmpty()
                    || filePath.trim().isEmpty()) {

                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "All fields are required."
                );
                return;
            }

            Evidence existingEvidence =
                    evidenceDAO.getEvidenceById(evidenceId);

            if (existingEvidence == null) {
                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Evidence record not found."
                );
                return;
            }

            evidenceDAO.updateEvidence(
                    evidenceId,
                    evidenceType.trim(),
                    description.trim(),
                    filePath.trim()
            );

            session.setAttribute(
                    "evidenceMessage",
                    "Evidence updated successfully."
            );

            response.sendRedirect("evidence");

        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid evidence ID."
            );
        }
    }
}
