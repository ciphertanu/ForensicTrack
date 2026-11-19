
package com.forensictrack.forensictrackhibernate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteEvidence")
public class DeleteEvidenceServlet extends HttpServlet {

    private EvidenceDAO evidenceDAO;

    @Override
    public void init() throws ServletException {
        evidenceDAO = SpringUtil.getBean(
                getServletContext(),
                EvidenceDAO.class
        );
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            int evidenceId = Integer.parseInt(
                    request.getParameter("evidenceId")
            );

            boolean deleted = evidenceDAO.deleteEvidence(evidenceId);

            session.setAttribute(
                    "message",
                    deleted
                            ? "Evidence deleted successfully."
                            : "Evidence cannot be deleted because custody records exist."
            );

        } catch (NumberFormatException e) {
            session.setAttribute("message", "Invalid evidence ID.");
        }

        response.sendRedirect("evidence");
    }
}
