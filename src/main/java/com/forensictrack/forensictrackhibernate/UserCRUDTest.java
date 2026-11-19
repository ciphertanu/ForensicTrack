package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.User;

public class UserCRUDTest {

    public static void main(String[] args) {

        UserDAO dao = new UserDAO();

        // =========================
        // CREATE
        // =========================

        User newUser = new User(
                "Hibernate Test User",
                "hibernate.test@gmail.com",
                "hibernate_test_user",
                "test123",
                "Investigator"
        );

        dao.createUser(newUser);

        // =========================
        // READ
        // =========================

        dao.readUsers();

        // =========================
        // UPDATE
        // =========================

        dao.updateUser(
                newUser.getUserId(),
                "Updated Hibernate User",
                "updated.hibernate@gmail.com",
                "Admin"
        );

        // READ after UPDATE
        dao.readUsers();

        // =========================
        // DELETE
        // =========================

        dao.deleteUser(newUser.getUserId());

        // READ after DELETE
        dao.readUsers();

        HibernateUtil.shutdown();
    }
}