package service.impl;

import model.Region;
import model.Writer;
import repository.RegionRepository;
import repository.impl.RegionRepositoryImpl;
import service.RegionService;

import java.util.List;

public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    public RegionServiceImpl() {
        regionRepository = new RegionRepositoryImpl();
    }


    @Override
    public Region getById(Long id) {
        return regionRepository.getById(id);
    }


    @Override
    public Region create(Writer writerId, String regionName) {
        return regionRepository.create(writerId, regionName);
    }


    @Override
    public Region update(Long id, Writer writerId, String regionName) {
        return regionRepository.update(id, writerId, regionName);
    }


    @Override
    public void deleteById(Long id) {
        regionRepository.deleteById(id);
    }

    @Override
    public List<Region> getAll() {
        return regionRepository.getAll();
    }
}
