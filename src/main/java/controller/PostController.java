package controller;

import model.Post;
import model.Writer;
import service.PostService;
import service.impl.PostServiceImpl;

import java.util.List;

public class PostController {

    private final PostService postService;

    public PostController() {
        this.postService = new PostServiceImpl();
    }

    public Post getById(Long id) {
        return postService.getById(id);
    }

    public Post create(Writer writerId, String content) {
        return postService.create(writerId, content);
    }

    public Post update(Long id, Writer writerId, String content) {
        return postService.update(id, writerId, content);
    }

    public void deleteById(Long id) {
        postService.deleteById(id);
    }

    public List<Post> getAll() {
        return postService.getAll();
    }
}
