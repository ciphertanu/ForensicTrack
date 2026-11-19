
package com.forensictrack.forensictrackhibernate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteCaseNote")
public class DeleteCaseNoteServlet extends HttpServlet {

    private CaseNoteDAO noteDAO;

    @Override
    public void init() throws ServletException {
        noteDAO = SpringUtil.getBean(
                getServletContext(),
                CaseNoteDAO.class
        );
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
            int noteId = Integer.parseInt(
                    request.getParameter("noteId")
            );

            if (noteDAO.getNoteById(noteId) == null) {
                session.setAttribute(
                        "caseNoteMessage",
                        "Case note not found."
                );
            } else {
                noteDAO.deleteNote(noteId);

                session.setAttribute(
                        "caseNoteMessage",
                        "Case note deletion request processed."
                );
            }

            response.sendRedirect("caseNotes");

        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid note ID."
            );
        }
    }
}
