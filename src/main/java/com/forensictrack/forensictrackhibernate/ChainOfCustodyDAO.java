
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.ChainOfCustody;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class ChainOfCustodyDAO {

    // CREATE
    public void createCustody(ChainOfCustody custody) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(custody);
            transaction.commit();

            System.out.println("Custody record created successfully!");

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackError) {
                    System.err.println("Rollback failed:");
                    rollbackError.printStackTrace();
                }
            }

            System.err.println("Error creating custody record:");
            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to save chain of custody record.", e
            );
        }
    }

    // READ ALL RECORDS
    public void readCustodyRecords() {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            List<ChainOfCustody> records =
                    session.createQuery(
                            "FROM ChainOfCustody ORDER BY custodyId DESC",
                            ChainOfCustody.class
                    ).getResultList();

            for (ChainOfCustody record : records) {
                System.out.println("Custody ID: " + record.getCustodyId());
                System.out.println("Evidence ID: " + record.getEvidenceId());
                System.out.println("Handled By: " + record.getHandledBy());
                System.out.println("Action Taken: " + record.getActionTaken());
                System.out.println("Action Date: " + record.getActionDate());
                System.out.println("--------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET ALL RECORDS FOR JSP
    public List<ChainOfCustody> getAllCustodyRecords() {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM ChainOfCustody ORDER BY custodyId DESC",
                    ChainOfCustody.class
            ).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // GET RECORD BY ID
    public ChainOfCustody getCustodyById(int custodyId) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.get(ChainOfCustody.class, custodyId);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // UPDATE
    public void updateCustody(int custodyId,
                              String newHandledBy,
                              String newActionTaken) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            ChainOfCustody custody =
                    session.get(ChainOfCustody.class, custodyId);

            if (custody == null) {
                transaction.rollback();
                return;
            }

            custody.setHandledBy(newHandledBy);
            custody.setActionTaken(newActionTaken);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackError) {
                    rollbackError.printStackTrace();
                }
            }

            e.printStackTrace();
            throw new RuntimeException("Unable to update custody record.", e);
        }
    }

    // DELETE
    public void deleteCustody(int custodyId) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            ChainOfCustody custody =
                    session.get(ChainOfCustody.class, custodyId);

            if (custody == null) {
                transaction.rollback();
                return;
            }

            session.remove(custody);
            transaction.commit();

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackError) {
                    rollbackError.printStackTrace();
                }
            }

            e.printStackTrace();
            throw new RuntimeException("Unable to delete custody record.", e);
        }
    }
}
