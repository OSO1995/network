package nc.projects.network.network;

import nc.projects.network.network.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StartTest {
    public static void main(String[] args) {
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");

        session.save(role);
    }
}
