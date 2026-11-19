
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class UserDAO {

    // CREATE
    public void createUser(User user) {
        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // READ ALL USERS
    public void readUsers() {
        for (User user : getAllUsers()) {
            System.out.println("User ID: " + user.getUserId());
            System.out.println("Full Name: " + user.getFullName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Role: " + user.getRole());
            System.out.println("------------------------------");
        }
    }

    // GET ALL USERS
    public List<User> getAllUsers() {
        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM User ORDER BY userId DESC",
                    User.class
            ).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // GET USER BY ID
    public User getUserById(int userId) {
        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.get(User.class, userId);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // UPDATE USER
    public boolean updateUser(int userId, String newFullName,
                              String newEmail, String newRole) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            User user = session.get(User.class, userId);

            if (user == null) {
                transaction.rollback();
                return false;
            }

            // Check whether another user already has this email
            Long emailCount = session.createQuery(
                    "SELECT COUNT(u) FROM User u "
                    + "WHERE u.email = :email AND u.userId <> :userId",
                    Long.class
            )
            .setParameter("email", newEmail)
            .setParameter("userId", userId)
            .uniqueResult();

            if (emailCount != null && emailCount > 0) {
                transaction.rollback();
                return false;
            }

            user.setFullName(newFullName);
            user.setEmail(newEmail);
            user.setRole(newRole);

            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // DELETE USER SAFELY
    public boolean deleteUser(int userId) {
        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            User user = session.get(User.class, userId);

            if (user == null) {
                transaction.rollback();
                return false;
            }

            // Do not delete users assigned to existing cases
            Long caseCount = session.createQuery(
                    "SELECT COUNT(c) FROM Case c "
                    + "WHERE c.assignedUserId = :userId",
                    Long.class
            )
            .setParameter("userId", userId)
            .uniqueResult();

            // Do not delete users who authored case notes
            Long noteCount = session.createQuery(
                    "SELECT COUNT(n) FROM CaseNote n "
                    + "WHERE n.investigatorId = :userId",
                    Long.class
            )
            .setParameter("userId", userId)
            .uniqueResult();

            if ((caseCount != null && caseCount > 0)
                    || (noteCount != null && noteCount > 0)) {

                transaction.rollback();
                return false;
            }

            session.remove(user);
            transaction.commit();

            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // LOGIN
    public User loginUser(String username, String password) {
        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM User WHERE username = :username "
                    + "AND password = :password",
                    User.class
            )
            .setParameter("username", username)
            .setParameter("password", password)
            .uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // REGISTER USER
    public boolean registerUser(User user) {
        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            Long usernameCount = session.createQuery(
                    "SELECT COUNT(u) FROM User u "
                    + "WHERE u.username = :username",
                    Long.class
            )
            .setParameter("username", user.getUsername())
            .uniqueResult();

            Long emailCount = session.createQuery(
                    "SELECT COUNT(u) FROM User u "
                    + "WHERE u.email = :email",
                    Long.class
            )
            .setParameter("email", user.getEmail())
            .uniqueResult();

            if ((usernameCount != null && usernameCount > 0)
                    || (emailCount != null && emailCount > 0)) {
                return false;
            }

            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();

            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
