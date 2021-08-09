package service;

import model.Region;
import model.Writer;

public interface WriterService extends GenericService<Writer, Long>{

    Writer create (String firstName, String lastName, String regionName);
    Writer update (Long id, String firstName, String lastName, String regionName);
}
