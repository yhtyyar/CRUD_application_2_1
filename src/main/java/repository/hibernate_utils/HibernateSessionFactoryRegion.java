package repository.hibernate_utils;

import model.Region;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryRegion {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryRegion() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/postgres",
                    "login", "password").load();
            flyway.migrate();

            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Region.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение! " + e);
            }
        }
        return sessionFactory;
    }
}
