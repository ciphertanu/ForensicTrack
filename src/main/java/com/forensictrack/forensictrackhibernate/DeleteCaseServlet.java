
package com.forensictrack.forensictrackhibernate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteCase")
public class DeleteCaseServlet extends HttpServlet {

    private CaseDAO caseDAO;

    @Override
    public void init() throws ServletException {
        caseDAO = SpringUtil.getBean(
                getServletContext(),
                CaseDAO.class
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
            int caseId = Integer.parseInt(
                    request.getParameter("caseId")
            );

            boolean deleted = caseDAO.deleteCase(caseId);

            session.setAttribute(
                    "message",
                    deleted
                            ? "Case deleted successfully."
                            : "Case cannot be deleted because related evidence or case notes exist."
            );

        } catch (NumberFormatException e) {
            session.setAttribute(
                    "message",
                    "Invalid case ID."
            );
        }

        response.sendRedirect("cases");
    }
}
