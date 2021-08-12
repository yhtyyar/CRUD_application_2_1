package repository.impl;

import model.Post;
import model.Region;
import model.Writer;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HibernateSessionFactory {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {}

    public static SessionFactory getSessionFactory() {

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (sessionFactory == null) {

            Flyway flyway = Flyway.configure().dataSource(properties.getProperty("url"),
                    properties.getProperty("username"), properties.getProperty("password")).load();
            flyway.baseline();
            flyway.migrate();


            try {
                Configuration configuration = new Configuration().configure();

                configuration.addAnnotatedClass(Post.class);
                configuration.addAnnotatedClass(Region.class);
                configuration.addAnnotatedClass(Writer.class);

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
