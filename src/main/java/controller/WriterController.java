package controller;

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

    public Writer create(String firstName, String lastName, String regionName) {
        return writerService.create(firstName, lastName,  regionName);
    }

    public Writer update(Long id, String firstName, String lastName, String regionName) {
        return writerService.update(id, firstName, lastName, regionName);
    }

    public void deleteById(Long id) {
        writerService.deleteById(id);
    }

    public List<Writer> getAll() {
        return writerService.getAll();
    }
}
