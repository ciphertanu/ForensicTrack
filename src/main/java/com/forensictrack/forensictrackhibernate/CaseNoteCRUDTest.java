package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.CaseNote;
import java.time.LocalDateTime;

public class CaseNoteCRUDTest {

    public static void main(String[] args) {

        CaseNoteDAO dao = new CaseNoteDAO();

        // =========================
        // CREATE
        // =========================

        CaseNote newNote = new CaseNote(
                1,
                2,
                "Initial forensic investigation note created using Hibernate.",
                LocalDateTime.now()
        );

        dao.createNote(newNote);

        // =========================
        // READ
        // =========================

        dao.readNotes();

        // =========================
        // UPDATE
        // =========================

        dao.updateNote(
                newNote.getNoteId(),
                "Updated forensic investigation note using Hibernate."
        );

        // READ after UPDATE
        dao.readNotes();

        // =========================
        // DELETE
        // =========================

        dao.deleteNote(newNote.getNoteId());

        // READ after DELETE
        dao.readNotes();

        HibernateUtil.shutdown();
    }
}