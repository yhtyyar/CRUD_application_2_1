package repository.impl;

import model.Post;
import model.Writer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.PostRepository;
import repository.hibernate_utils.HibernateSessionFactoryPost;

import java.time.LocalDateTime;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {


    @Override
    public Post getById(Long id) {
        return HibernateSessionFactoryPost.getSessionFactory().openSession().get(Post.class, id);
    }


    @Override
    public Post create(Writer writerId, String content) {

        Session session = HibernateSessionFactoryPost.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Integer postId = (Integer) session.save(new Post(writerId, content, LocalDateTime.now(),
                LocalDateTime.now()));
        Post post = session.get(Post.class, postId);
        transaction.commit();
        session.close();

        return post;
    }


    @Override
    public Post update(Long id, Writer writerId, String content) {

        Session session = HibernateSessionFactoryPost.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Post post = session.get(Post.class, id);
        post.setWriter(writerId);
        post.setContent(content);
        post.setUpdated(LocalDateTime.now());

        session.update(post);
        Post postResult = session.get(Post.class, id);
        transaction.commit();
        session.close();

        return postResult;
    }


    @Override
    public void deleteById(Long id) {

        Session session = HibernateSessionFactoryPost.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Post post = session.get(Post.class,id);
        session.delete(post);
        transaction.commit();
        session.close();
    }


    @Override
    public List<Post> getAll() {

        Session session = HibernateSessionFactoryPost.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<Post> postList =  session.createQuery("SELECT P.id, P.writer, P.created, P.updated, P.content " +
                "FROM Post AS P", Post.class).list();

        transaction.commit();
        session.close();

        return postList;
    }
}
