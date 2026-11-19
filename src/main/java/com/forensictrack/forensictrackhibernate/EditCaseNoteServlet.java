
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.CaseNote;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/editCaseNote")
public class EditCaseNoteServlet extends HttpServlet {

    private CaseNoteDAO noteDAO;

    @Override
    public void init() throws ServletException {
        noteDAO = SpringUtil.getBean(
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

        try {
            int noteId = Integer.parseInt(
                    request.getParameter("id")
            );

            CaseNote note = noteDAO.getNoteById(noteId);

            if (note == null) {
                response.sendError(404, "Case note not found.");
                return;
            }

            request.setAttribute("caseNote", note);

            request.getRequestDispatcher("/editCaseNote.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(400, "Invalid note ID.");
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
            int noteId = Integer.parseInt(
                    request.getParameter("noteId")
            );

            String noteText = request.getParameter("note");

            if (noteText == null || noteText.trim().isEmpty()) {
                response.sendError(400, "Note cannot be empty.");
                return;
            }

            CaseNote existingNote = noteDAO.getNoteById(noteId);

            if (existingNote == null) {
                response.sendError(404, "Case note not found.");
                return;
            }

            noteDAO.updateNote(noteId, noteText.trim());

            session.setAttribute(
                    "caseNoteMessage",
                    "Case note updated successfully."
            );

            response.sendRedirect("caseNotes");

        } catch (NumberFormatException e) {
            response.sendError(400, "Invalid note ID.");
        }
    }
}
