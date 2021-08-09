package repository;

import model.Region;
import model.Writer;

public interface WriterRepository   extends GenericRepository <Writer, Long> {

    Writer create (String firstName, String lastName);
    Writer update (Long id, String firstName, String lastName, Region region);


}
