package service;

import model.Post;
import model.Writer;

public interface PostService extends GenericService<Post, Long>{

    Post create(Long writerId, String content);
    Post update (Long id, Long writerId, String content);
}
