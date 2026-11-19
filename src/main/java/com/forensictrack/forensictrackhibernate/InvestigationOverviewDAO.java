
package com.forensictrack.forensictrackhibernate;

import org.hibernate.Session;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InvestigationOverviewDAO {

    // 1. Get total number of cases
    public long getTotalCases() {
        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                    "SELECT COUNT(c) FROM Case c",
                    Long.class
            ).uniqueResult();

            return count != null ? count : 0;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 2. Get number of open cases
    public long getOpenCases() {
        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                    "SELECT COUNT(c) FROM Case c "
                    + "WHERE LOWER(c.status) = 'open'",
                    Long.class
            ).uniqueResult();

            return count != null ? count : 0;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 3. Get total number of evidence records
    public long getTotalEvidence() {
        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                    "SELECT COUNT(e) FROM Evidence e",
                    Long.class
            ).uniqueResult();

            return count != null ? count : 0;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 4. Get total chain of custody records
    public long getTotalCustodyRecords() {
        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                    "SELECT COUNT(c) FROM ChainOfCustody c",
                    Long.class
            ).uniqueResult();

            return count != null ? count : 0;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 5. Get cases grouped by status
    public Map<String, Long> getCasesByStatus() {

        Map<String, Long> result = new LinkedHashMap<>();

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            List<Object[]> rows = session.createQuery(
                    "SELECT c.status, COUNT(c) "
                    + "FROM Case c GROUP BY c.status",
                    Object[].class
            ).getResultList();

            for (Object[] row : rows) {

                String status = row[0] == null
                        ? "Not specified"
                        : row[0].toString();

                Long count = (Long) row[1];

                result.put(status, count);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // 6. Get cases grouped by priority
    public Map<String, Long> getCasesByPriority() {

        Map<String, Long> result = new LinkedHashMap<>();

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            List<Object[]> rows = session.createQuery(
                    "SELECT c.priority, COUNT(c) "
                    + "FROM Case c GROUP BY c.priority",
                    Object[].class
            ).getResultList();

            for (Object[] row : rows) {

                String priority = row[0] == null
                        ? "Not specified"
                        : row[0].toString();

                Long count = (Long) row[1];

                result.put(priority, count);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
