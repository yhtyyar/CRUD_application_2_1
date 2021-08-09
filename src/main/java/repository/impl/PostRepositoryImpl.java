package repository.impl;

import model.Post;
import model.Writer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.PostRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {

    private Transaction transaction;


    @Override
    public Post getById(Long id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Post.class, id);
    }


    @Override
    public Post create(Writer writerId, String content) {

        Post post = new Post();

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Long postId = (Long) session.save(new Post(writerId, content, LocalDateTime.now(),
                    LocalDateTime.now()));
            post = session.get(Post.class, postId);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }

        return post;
    }


    @Override
    public Post update(Long id, Writer writerId, String content) {

        Post postResult = new Post();

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Post post = session.get(Post.class, id);
            post.setWriter(writerId);
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

            postList = session.createQuery("SELECT P.id, P.writer, P.created, P.updated, P.content " +
                    "FROM Post AS P", Post.class).list();

            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }

        return postList;
    }
}
