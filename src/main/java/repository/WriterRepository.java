package repository;

import model.Writer;

public interface WriterRepository   extends GenericRepository <Writer, Long> {

    Writer create (String firstName, String lastName, String regionName);
    Writer update (Long id, String firstName, String lastName, String regionName);


}
