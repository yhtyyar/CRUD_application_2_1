package repository;

import model.Post;
import model.Writer;

public interface PostRepository extends GenericRepository <Post, Long> {

    Post create (Writer writerId, String content);
    Post update (Long id, Writer writerId, String content);
}
