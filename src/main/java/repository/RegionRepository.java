package repository;

import model.Region;
import model.Writer;

public interface RegionRepository extends GenericRepository <Region, Long> {

    Region create (Writer writerId, String regionName);
    Region update (Long id, Writer writerId, String regionName);
}
