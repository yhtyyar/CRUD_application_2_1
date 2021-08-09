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
    public Writer create(String firstName, String lastName) {
        return writerRepository.create(firstName, lastName);
    }


    @Override
    public Writer update(Long id, String firstName, String lastName, Region region) {
        return writerRepository.update(id, firstName, lastName, region);
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
