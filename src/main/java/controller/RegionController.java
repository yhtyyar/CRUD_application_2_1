package controller;

import model.Region;
import model.Writer;
import service.RegionService;
import service.impl.RegionServiceImpl;

import java.util.List;

public class RegionController {

    private final RegionService regionService;

    public RegionController () {
        this.regionService = new RegionServiceImpl();
    }


    public Region getById(Long id) {
        return regionService.getById(id);
    }

    public Region create(Writer writerId, String regionName) {
        return regionService.create(writerId, regionName);
    }

    public Region update(Long id,Writer writerId, String regionName) {
        return regionService.update(id,writerId, regionName);
    }

    public void deleteById(Long id) {
        regionService.deleteById(id);
    }

    public List<Region> getAll() {
        return regionService.getAll();
    }
}
