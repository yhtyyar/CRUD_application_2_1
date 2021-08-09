package repository.impl;

import model.Region;
import model.Writer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.WriterRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static repository.impl.SQLRequests.*;

public class WriterRepositoryImpl implements WriterRepository {

    private Transaction transaction;

    @Override
    public Writer getById(Long id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Writer.class, id);
    }


    @Override
    public Writer create(String firstName, String lastName) {

        Writer writer = new Writer();

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Long writerId = (Long) session.save(new Writer(firstName, lastName));

            session.createSQLQuery(String.format(WRITER_CREATE_POST, writerId,
                    Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now())));

            session.createSQLQuery(String.format(WRITER_CREATE_REGION, writerId));

            writer = session.get(Writer.class, writerId);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }

        return writer;
    }


    @Override
    public Writer update(Long id, String firstName, String lastName, Region region) {

        Writer writerResult = new Writer();

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Writer writer = session.get(Writer.class, id);
            writer.setFirstName(firstName);
            writer.setLastName(lastName);
            writer.setRegion(region);

            session.update(writer);

            writerResult = session.get(Writer.class, id);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }

        return writerResult;
    }


    @Override
    public void deleteById(Long id) {

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Writer writer = session.get(Writer.class, id);

            session.delete(writer);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }

    }


    @Override
    public List<Writer> getAll() {

        List<Writer> writerList = new ArrayList<>();

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            writerList = session.createQuery("SELECT W.id, W.firstName, W.lastName, W.region, " +
                    "W.posts FROM Writer AS W ", Writer.class).list();

            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }

        return writerList;
    }
}
