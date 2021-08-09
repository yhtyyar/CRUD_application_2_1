package service.impl;

import model.Post;
import repository.PostRepository;
import repository.impl.PostRepositoryImpl;
import service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl() {
        postRepository = new PostRepositoryImpl();
    }

    @Override
    public Post getById(Long id) {
        return postRepository.getById(id);
    }


    @Override
    public Post create(Long writerId, String content) {
        return postRepository.create(writerId, content);
    }


    @Override
    public Post update(Long id, Long writerId, String content) {
        return postRepository.update(id, writerId, content);
    }


    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }


    @Override
    public List<Post> getAll() {
        return postRepository.getAll();
    }
}
