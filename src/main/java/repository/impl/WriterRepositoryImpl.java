package repository.impl;

import model.Region;
import model.Writer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.PostRepository;
import repository.RegionRepository;
import repository.WriterRepository;

import java.util.ArrayList;
import java.util.List;


public class WriterRepositoryImpl implements WriterRepository {

    private Transaction transaction;

    private final RegionRepository regionRepository;
    private final PostRepository postRepository;

    public WriterRepositoryImpl() {
        regionRepository = new  RegionRepositoryImpl();
        postRepository = new PostRepositoryImpl();
    }

    @Override
    public Writer getById(Long id) {

        Writer writer = new Writer();

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            writer = session.get(Writer.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return writer;
    }


    @Override
    public Writer create(String firstName, String lastName, String regionName) {

        Writer writer = new Writer();
        Long writerId = 0L;

        Region region = regionRepository.create(regionName);

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            writerId = (Long) session.save(new Writer(firstName, lastName, region));

            writer = session.get(Writer.class, writerId);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }

        postRepository.create(writerId, "нет записи");

        return writer;
    }


    @Override
    public Writer update(Long id, String firstName, String lastName, String regionName) {

        Writer writerResult = new Writer();

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Writer writer = session.get(Writer.class, id);
            writer.setFirstName(firstName);
            writer.setLastName(lastName);
            writer.setRegion(new Region(regionName));

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

            writerList = session.createQuery(" FROM Writer", Writer.class).list();

            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }

        return writerList;
    }
}
