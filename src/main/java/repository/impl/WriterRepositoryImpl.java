package repository.impl;

import model.Post;
import model.Writer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.WriterRepository;
import repository.hibernate_utils.HibernateSessionFactoryWriter;

import java.time.LocalDateTime;
import java.util.List;

public class WriterRepositoryImpl implements WriterRepository {


    @Override
    public Writer getById(Long id) {
        return HibernateSessionFactoryWriter.getSessionFactory().openSession().get(Writer.class, id);
    }


    @Override
    public Writer create(String firstName, String lastName) {

        Session session = HibernateSessionFactoryWriter.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Post post = new Post();
        post.setCreated(LocalDateTime.now());
        post.setUpdated(LocalDateTime.now());

        Integer writerId = (Integer) session.save(new Writer(firstName, lastName));
        Writer writer = session.get(Writer.class, writerId);
        transaction.commit();
        session.close();

        return writer;
    }


    @Override
    public Writer update(Long id, String firstName, String lastName) {

        Session session = HibernateSessionFactoryWriter.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Writer writer = session.get(Writer.class, id);
        writer.setFirstName(firstName);
        writer.setLastName(lastName);

        session.update(writer);
        Writer writerResult = session.get(Writer.class, id);
        transaction.commit();
        session.close();

        return writerResult;
    }


    @Override
    public void deleteById(Long id) {

        Session session = HibernateSessionFactoryWriter.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Writer writer = session.get(Writer.class, id);

        session.delete(writer);
        transaction.commit();
        session.close();
    }


    @Override
    public List<Writer> getAll() {

        Session session = HibernateSessionFactoryWriter.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<Writer> writerList = session.createQuery("SELECT W.id, W.firstName, W.lastName, W.region, " +
                "W.posts FROM Writer AS W", Writer.class).list();

        transaction.commit();
        session.close();

        return writerList;
    }
}
