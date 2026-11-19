package com.forensictrack.forensictrackhibernate;
import com.forensictrack.forensictrackhibernate.entity.Case;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.forensictrack.forensictrackhibernate.entity.User;
import com.forensictrack.forensictrackhibernate.entity.Evidence;
import com.forensictrack.forensictrackhibernate.entity.CaseNote;
import com.forensictrack.forensictrackhibernate.entity.ChainOfCustody;


public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Case.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Evidence.class)
                    .addAnnotatedClass(CaseNote.class)
                    .addAnnotatedClass(ChainOfCustody.class)
                    .buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}