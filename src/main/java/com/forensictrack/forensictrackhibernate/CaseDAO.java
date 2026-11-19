
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.Case;
import com.forensictrack.forensictrackhibernate.entity.Evidence;
import com.forensictrack.forensictrackhibernate.entity.CaseNote;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CaseDAO {

    // CREATE CASE
    public void createCase(Case caseObj) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(caseObj);

            transaction.commit();

            System.out.println("Case created successfully!");

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Error while creating case!");
            e.printStackTrace();
        }
    }

    // READ ALL CASES
    public void readCases() {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            List<Case> cases = session.createQuery(
                    "FROM Case", Case.class).getResultList();

            System.out.println("----- All Cases -----");

            for (Case caseObj : cases) {

                System.out.println("Case ID: " + caseObj.getCaseId());
                System.out.println("Title: " + caseObj.getCaseTitle());
                System.out.println("Type: " + caseObj.getCaseType());
                System.out.println("Description: " + caseObj.getDescription());
                System.out.println("Priority: " + caseObj.getPriority());
                System.out.println("Status: " + caseObj.getStatus());
                System.out.println("Created Date: " + caseObj.getCreatedDate());
                System.out.println("Assigned User ID: " + caseObj.getAssignedUserId());

                System.out.println("---------------------");
            }

        } catch (Exception e) {

            System.out.println("Error while reading cases!");
            e.printStackTrace();
        }
    }

    // GET ALL CASES FOR WEB APPLICATION
    public List<Case> getAllCases() {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM Case ORDER BY caseId DESC",
                    Case.class
            ).getResultList();

        } catch (Exception e) {

            System.out.println("Error retrieving cases!");
            e.printStackTrace();

            return new java.util.ArrayList<>();
        }
    }

    // GET CASE BY ID
    public Case getCaseById(int caseId) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.get(Case.class, caseId);

        } catch (Exception e) {

            System.out.println("Error retrieving case!");
            e.printStackTrace();

            return null;
        }
    }

    // UPDATE CASE
    public void updateCase(int caseId,
                           String newPriority,
                           String newStatus) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Case caseObj = session.get(Case.class, caseId);

            if (caseObj != null) {

                caseObj.setPriority(newPriority);
                caseObj.setStatus(newStatus);

                session.merge(caseObj);

                transaction.commit();

                System.out.println("Case updated successfully!");

            } else {

                transaction.rollback();
                System.out.println("Case not found!");
            }

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Error while updating case!");
            e.printStackTrace();
        }
    }

    // DELETE CASE SAFELY
    public boolean deleteCase(int caseId) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Case caseObj = session.get(Case.class, caseId);

            if (caseObj == null) {
                transaction.rollback();
                return false;
            }

            Long evidenceCount = session.createQuery(
                    "SELECT COUNT(e) FROM Evidence e WHERE e.caseId = :caseId",
                    Long.class)
                    .setParameter("caseId", caseId)
                    .uniqueResult();

            Long notesCount = session.createQuery(
                    "SELECT COUNT(n) FROM CaseNote n WHERE n.caseId = :caseId",
                    Long.class)
                    .setParameter("caseId", caseId)
                    .uniqueResult();

            if ((evidenceCount != null && evidenceCount > 0)
                    || (notesCount != null && notesCount > 0)) {

                transaction.rollback();
                System.out.println(
                        "Deletion blocked: Related evidence or notes exist.");

                return false;
            }

            session.remove(caseObj);

            transaction.commit();

            System.out.println("Case deleted successfully!");

            return true;

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Error while deleting case!");
            e.printStackTrace();

            return false;
        }
    }
}
