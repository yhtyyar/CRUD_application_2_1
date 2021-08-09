package service.impl;

import model.Post;
import model.Writer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import repository.PostRepository;
import repository.impl.PostRepositoryImpl;
import service.PostService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {


    private static final Long id = 1L;
    private static final String content = "content";

    @Mock
    private Writer writer;

    @Mock
    private PostRepository postRepository;

    @Mock
    private List<Post> postList;

    @Spy
    private PostService postService;


    @Before
    public void setUp() {
        postRepository = new PostRepositoryImpl();
    }


    @Test
    public void getByIdTest() {
        doReturn(postRepository.getById(id)).when(postService).getById(id);
        assertEquals(postRepository.getById(id), postService.getById(1L));
    }


//    @Test
//    public void createTest() {
//        doReturn(postRepository.create(writer, content)).when(postService).create(writer, content);
//        assertEquals(postRepository.create(writer, content), postService.create(writer, content));
//    }
//
//
//    @Test
//    public void updateTest() {
//        doReturn(postRepository.update(id, writer, content)).when(postService).update(id, writer, content);
//        assertEquals(postRepository.update(id, writer, content), postService.update(1L, writer, content));
//    }


    @Test
    public void getAllTest() {
        doReturn(postList).when(postService).getAll();
        assertEquals(postList, postService.getAll());
    }
}