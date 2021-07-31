package service;

import model.Post;
import model.Writer;

public interface PostService extends GenericService<Post, Long>{

    Post create(Writer writerId, String content);
    Post update (Long id, Writer writerId, String content);
}
