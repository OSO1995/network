package nc.project.network;

import nc.project.network.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class startTest {
    public static void main(String[] args) {
//        buildSessionFactory();
//        SessionFactory sessionFactory = getSessionFactory();
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Role role = new Role();
        role.setName("ROLE_USER");

        session.save(role);
//        shutdown();
    }

//    private static final SessionFactory sessionFactory = buildSessionFactory();
//
//    private static SessionFactory buildSessionFactory() {
//        try {
//            // Create the SessionFactory from hibernate.cfg.xml
//            return new AnnotationConfiguration().configure().buildSessionFactory();
//
//        } catch (Throwable ex) {
//            // Make sure you log the exception, as it might be swallowed
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public static void shutdown() {
//        // Close caches and connection pools
//        getSessionFactory().close();
//    }


}
