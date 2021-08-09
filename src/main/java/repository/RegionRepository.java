package repository;

import model.Region;
import model.Writer;

public interface RegionRepository extends GenericRepository <Region, Long> {

    Region create ( String regionName);
    Region update (Long id, String regionName);
}
