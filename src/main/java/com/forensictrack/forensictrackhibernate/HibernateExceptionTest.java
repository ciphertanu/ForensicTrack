package com.forensictrack.forensictrackhibernate;
import com.forensictrack.forensictrackhibernate.entity.User;
import java.util.logging.Level;
import java.util.logging.Logger;
public class HibernateExceptionTest {
public static void main(String[] args) {
Logger.getLogger("org.hibernate").setLevel(Level.OFF);
Logger.getLogger("org.hibernate.orm").setLevel(Level.OFF);
UserDAO dao = new UserDAO();
try {
System.out.println("========== EXCEPTION HANDLING TEST ==========");
// Attempt to create a user with an existing username
User duplicateUser = new User(
"Duplicate Test User",
"duplicate@gmail.com",
"admin",
"test123",
"Investigator"
);
dao.createUser(duplicateUser);
} catch (Exception e) {
System.out.println("Exception handled successfully!");
System.out.println("Error Code: 1062 - Duplicate username 'admin' already exists.");
} finally {
HibernateUtil.shutdown();
}
}
}