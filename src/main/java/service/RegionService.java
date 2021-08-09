package service;

import model.Region;
import model.Writer;

public interface RegionService extends GenericService<Region, Long> {

    Region create( String regionName);
    Region update (Long id, String regionName);
}
