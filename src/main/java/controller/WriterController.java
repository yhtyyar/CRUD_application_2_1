package controller;

import model.Region;
import model.Writer;
import service.WriterService;
import service.impl.WriterServiceImpl;

import java.util.List;

public class WriterController {

    private final WriterService writerService;

    public WriterController() {
        this.writerService = new WriterServiceImpl();
    }

    public Writer getById(Long id) {
        return writerService.getById(id);
    }

    public Writer create(String firstName, String lastName) {
        return writerService.create(firstName, lastName);
    }

    public Writer update(Long id, String firstName, String lastName, Region region) {
        return writerService.update(id, firstName, lastName, region);
    }

    public void deleteById(Long id) {
        writerService.deleteById(id);
    }

    public List<Writer> getAll() {
        return writerService.getAll();
    }
}
