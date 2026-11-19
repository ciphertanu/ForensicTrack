
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.Evidence;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EvidenceDAO {

    // CREATE EVIDENCE
    public void createEvidence(Evidence evidence) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(evidence);

            transaction.commit();

            System.out.println("Evidence created successfully!");

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Error while creating evidence!");
            e.printStackTrace();
        }
    }

    // READ ALL EVIDENCE
    public void readEvidence() {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            List<Evidence> evidenceList = session.createQuery(
                    "FROM Evidence", Evidence.class).getResultList();

            for (Evidence evidence : evidenceList) {

                System.out.println("Evidence ID: " + evidence.getEvidenceId());
                System.out.println("Case ID: " + evidence.getCaseId());
                System.out.println("Evidence Type: " + evidence.getEvidenceType());
                System.out.println("Description: " + evidence.getDescription());
                System.out.println("File Path: " + evidence.getFilePath());
                System.out.println("Collected Date: " + evidence.getCollectedDate());

                System.out.println("-----------------------------");
            }

        } catch (Exception e) {

            System.out.println("Error while reading evidence!");
            e.printStackTrace();
        }
    }

    // GET ALL EVIDENCE FOR JSP
    public List<Evidence> getAllEvidence() {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM Evidence ORDER BY evidenceId DESC",
                    Evidence.class
            ).getResultList();

        } catch (Exception e) {

            System.out.println("Error retrieving evidence!");
            e.printStackTrace();

            return new java.util.ArrayList<>();
        }
    }

    // GET EVIDENCE BY ID
    public Evidence getEvidenceById(int evidenceId) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.get(Evidence.class, evidenceId);

        } catch (Exception e) {

            System.out.println("Error retrieving evidence!");
            e.printStackTrace();

            return null;
        }
    }

    // UPDATE EVIDENCE
    public void updateEvidence(int evidenceId,
                               String newEvidenceType,
                               String newDescription,
                               String newFilePath) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Evidence evidence =
                    session.get(Evidence.class, evidenceId);

            if (evidence != null) {

                evidence.setEvidenceType(newEvidenceType);
                evidence.setDescription(newDescription);
                evidence.setFilePath(newFilePath);

                transaction.commit();

                System.out.println("Evidence updated successfully!");

            } else {

                transaction.rollback();
                System.out.println("Evidence not found!");
            }

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Error while updating evidence!");
            e.printStackTrace();
        }
    }

    // DELETE EVIDENCE SAFELY
    public boolean deleteEvidence(int evidenceId) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Evidence evidence =
                    session.get(Evidence.class, evidenceId);

            if (evidence == null) {
                transaction.rollback();
                return false;
            }

            Long custodyCount = session.createQuery(
                    "SELECT COUNT(c) FROM ChainOfCustody c "
                    + "WHERE c.evidenceId = :evidenceId",
                    Long.class)
                    .setParameter("evidenceId", evidenceId)
                    .uniqueResult();

            if (custodyCount != null && custodyCount > 0) {

                transaction.rollback();

                System.out.println(
                        "Deletion blocked: Chain of custody records exist.");

                return false;
            }

            session.remove(evidence);

            transaction.commit();

            System.out.println("Evidence deleted successfully!");

            return true;

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Error while deleting evidence!");
            e.printStackTrace();

            return false;
        }
    }
}
