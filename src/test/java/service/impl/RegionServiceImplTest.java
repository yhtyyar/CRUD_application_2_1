package service.impl;


import model.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.RegionService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class RegionServiceImplTest {


    private static final Long id = 1L;
    private static final String regionName = "Barcelona";

    @Mock
    private Region region;

    @Mock
    private List<Region> regionList;

    @Mock
    private RegionService regionService;


    @Test
    public void getByIdTest() {
        doReturn(region).when(regionService).getById(id);
        assertEquals(region, regionService.getById(1L));
    }


    @Test
    public void createTest() {
        doReturn(region).when(regionService).create("Barcelona");
        assertEquals(region, regionService.create(regionName));
    }


    @Test
    public void updateTest() {
        doReturn(region).when(regionService).update(id,"Barcelona");
        assertEquals(region, regionService.update(1L,"Barcelona"));
    }


    @Test
    public void getAllTest() {
        doReturn(regionList).when(regionService).getAll();
        assertEquals(regionList, regionService.getAll());
    }

}
