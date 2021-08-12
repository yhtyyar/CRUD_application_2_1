package service.impl;

import model.Writer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import service.WriterService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WriterServiceImplTest {

    private static final Long id = 1L;
    private static final String first = "Sergey";
    private static final String last = "Dovlatov";
    private static final String region = "Asia";


    @Mock
    private Writer writer;

    @Mock
    private List<Writer> writerList;

    @Mock
    private WriterService writerService;


    @Before
    public void setUp() {

        String firstName = "Viktor";
        String lastName = "Pelevin";

        when(writer.getFirstName()).thenReturn(firstName);
        when(writer.getLastName()).thenReturn(lastName);
    }


    @Test
    public void getWriterTest() {
        assertEquals("Viktor", writer.getFirstName());
        assertEquals("Pelevin", writer.getLastName());
    }


    @Test
    public void getByIdTest() {
        doReturn(writer).when(writerService).getById(id);
        assertEquals(writer, writerService.getById(1L));
    }


    @Test
    public void createTest() {
        doReturn(writer).when(writerService).create("Sergey", "Dovlatov", region);
        assertEquals(writer, writerService.create("Sergey", "Dovlatov", region));
    }


    @Test
    public void updateTest() {
        doReturn(writer).when(writerService).update(1L, "Sergey", "Dovlatov", region);
        assertEquals(writer, writerService.update(id, first, last, region));
    }

    @Test
    public void getAllTest() {
        doReturn(writerList).when(writerService).getAll();
        assertEquals(writerList, writerService.getAll());
    }

}
