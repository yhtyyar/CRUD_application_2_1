package repository.impl;

import model.Post;
import model.Writer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.PostRepository;
import repository.WriterRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {

    private Transaction transaction;

    public PostRepositoryImpl() {
    }

    @Override
    public Post getById(Long id) {

        Post post = new Post();

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            post = session.get(Post.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return post;
    }


    @Override
    public Post create(Long writerId, String content) {

        Post postResult = new Post();

        WriterRepository writerRepository = new WriterRepositoryImpl();
        Writer writer = writerRepository.getById(writerId);

        try  (Session session = HibernateSessionFactory.getSessionFactory().openSession()){

            transaction = session.beginTransaction();

            Post post = new Post();
            post.setWriter(writer);
            post.setContent(content);
            post.setCreated(LocalDateTime.now());
            post.setUpdated(LocalDateTime.now());

            Long postId = (Long) session.save(post);

            postResult = session.get(Post.class, postId);

            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }

        return postResult;
    }


    @Override
    public Post update(Long id, Long writerId, String content) {

        Post postResult = new Post();

        WriterRepository writerRepository = new WriterRepositoryImpl();
        Writer writer = writerRepository.getById(writerId);

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Post post = session.get(Post.class, id);
            post.setWriter(writer);
            post.setContent(content);
            post.setUpdated(LocalDateTime.now());

            session.update(post);

            postResult = session.get(Post.class, id);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }

        return postResult;
    }


    @Override
    public void deleteById(Long id) {

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Post post = session.get(Post.class, id);
            session.delete(post);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }
    }


    @Override
    public List<Post> getAll() {

        List<Post> postList = new ArrayList<>();

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            postList = session.createQuery("FROM Post", Post.class).list();

            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }

        return postList;
    }
}
