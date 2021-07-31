package service;

import model.Region;
import model.Writer;

public interface RegionService extends GenericService<Region, Long> {

    Region create(Writer writerId, String regionName);
    Region update (Long id,Writer writerId,  String regionName);
}
