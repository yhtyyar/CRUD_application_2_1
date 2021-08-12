package service.impl;

import model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.PostService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {


    private static final Long id = 1L;
    private static final String content = "content";

    @Mock
    private Post post;

    @Mock
    private List<Post> postList;

    @Mock
    private PostService postService;


    @Test
    public void getByIdTest() {
        doReturn(post).when(postService).getById(id);
        assertEquals(post, postService.getById(1L));
    }


    @Test
    public void createTest() {
        doReturn(post).when(postService).create(1L, content);
        assertEquals(post, postService.create(1L, content));
    }


    @Test
    public void updateTest() {
        doReturn(post).when(postService).update(id, 1L, content);
        assertEquals(post, postService.update(1L, 1L, content));
    }


    @Test
    public void getAllTest() {
        doReturn(postList).when(postService).getAll();
        assertEquals(postList, postService.getAll());
    }
}