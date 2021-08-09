package service.impl;

import model.Region;
import model.Writer;
import repository.WriterRepository;
import repository.impl.WriterRepositoryImpl;
import service.WriterService;

import java.util.List;

public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepository;

    public WriterServiceImpl() {
        writerRepository = new WriterRepositoryImpl();
    }

    @Override
    public Writer getById(Long id) {
        return writerRepository.getById(id);
    }


    @Override
    public Writer create(String firstName, String lastName, String regionName) {
        return writerRepository.create(firstName, lastName, regionName);
    }


    @Override
    public Writer update(Long id, String firstName, String lastName, String regionName) {
        return writerRepository.update(id, firstName, lastName, regionName);
    }


    @Override
    public void deleteById(Long id) {
        writerRepository.deleteById(id);
    }


    @Override
    public List<Writer> getAll() {
        return writerRepository.getAll();
    }
}
