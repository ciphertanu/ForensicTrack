
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.CaseNote;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CaseNoteDAO {

    // CREATE
    public void createNote(CaseNote caseNote) {
        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.persist(caseNote);
            transaction.commit();

            System.out.println("Case note created successfully!");

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error while creating case note!");
            e.printStackTrace();
        }
    }

    // READ ALL NOTES
    public void readNotes() {
        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            List<CaseNote> notes =
                    session.createQuery(
                            "FROM CaseNote ORDER BY noteId DESC",
                            CaseNote.class
                    ).getResultList();

            for (CaseNote note : notes) {
                System.out.println("Note ID: " + note.getNoteId());
                System.out.println("Case ID: " + note.getCaseId());
                System.out.println("Investigator ID: " + note.getInvestigatorId());
                System.out.println("Note: " + note.getNote());
                System.out.println("Note Date: " + note.getNoteDate());
                System.out.println("------------------------------------");
            }

        } catch (Exception e) {
            System.out.println("Error while reading case notes!");
            e.printStackTrace();
        }
    }

    // GET ALL NOTES FOR JSP
    public List<CaseNote> getAllNotes() {
        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM CaseNote ORDER BY noteId DESC",
                    CaseNote.class
            ).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    // GET ONE NOTE BY ID
    public CaseNote getNoteById(int noteId) {
        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.get(CaseNote.class, noteId);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // UPDATE
    public void updateNote(int noteId, String newNote) {
        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            CaseNote caseNote = session.get(CaseNote.class, noteId);

            if (caseNote != null) {
                caseNote.setNote(newNote);
                transaction.commit();

                System.out.println("Case note updated successfully!");
            } else {
                transaction.rollback();
                System.out.println("Case note not found!");
            }

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error while updating case note!");
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteNote(int noteId) {
        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            CaseNote caseNote = session.get(CaseNote.class, noteId);

            if (caseNote != null) {
                session.remove(caseNote);
                transaction.commit();

                System.out.println("Case note deleted successfully!");
            } else {
                transaction.rollback();
                System.out.println("Case note not found!");
            }

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error while deleting case note!");
            e.printStackTrace();
        }
    }
}
