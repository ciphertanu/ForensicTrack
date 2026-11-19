
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.CaseNote;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/caseNotes")
public class CaseNoteServlet extends HttpServlet {

    private CaseDAO caseDAO;
    private CaseNoteDAO caseNoteDAO;

    @Override
    public void init() throws ServletException {

        caseDAO = SpringUtil.getBean(
                getServletContext(),
                CaseDAO.class
        );

        caseNoteDAO = SpringUtil.getBean(
                getServletContext(),
                CaseNoteDAO.class
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
                "noteList",
                caseNoteDAO.getAllNotes()
        );

        request.getRequestDispatcher("/caseNotes.jsp")
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
            String noteText = request.getParameter("note");

            if (caseIdValue == null || noteText == null
                    || caseIdValue.trim().isEmpty()
                    || noteText.trim().isEmpty()) {

                request.setAttribute(
                        "error",
                        "Please enter the Case ID and investigation note."
                );

                request.getRequestDispatcher("/addCaseNote.jsp")
                        .forward(request, response);
                return;
            }

            int caseId = Integer.parseInt(caseIdValue.trim());

            noteText = noteText.trim();

            int investigatorId = Integer.parseInt(
                    session.getAttribute("userId").toString()
            );

            if (caseId <= 0) {

                request.setAttribute(
                        "error",
                        "Invalid Case ID."
                );

                request.getRequestDispatcher("/addCaseNote.jsp")
                        .forward(request, response);
                return;
            }

            if (caseDAO.getCaseById(caseId) == null) {

                request.setAttribute(
                        "error",
                        "The entered Case ID does not exist."
                );

                request.getRequestDispatcher("/addCaseNote.jsp")
                        .forward(request, response);
                return;
            }

            CaseNote caseNote = new CaseNote(
                    caseId,
                    investigatorId,
                    noteText,
                    LocalDateTime.now()
            );

            caseNoteDAO.createNote(caseNote);

            session.setAttribute(
                    "caseNoteMessage",
                    "Case note submitted successfully."
            );

            response.sendRedirect("caseNotes");

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Please enter a valid Case ID."
            );

            request.getRequestDispatcher("/addCaseNote.jsp")
                    .forward(request, response);
        }
    }
}
