package rzd.pktbcki.mapper;

import rzd.pktbcki.system.ApplicationSystem;

/**
 * Login: VNikishin
 * Date: 26.06.18
 * Time: 18:13
 */
public interface SystemMapper {
    ApplicationSystem findById(Integer systemId);
    Integer isExist(ApplicationSystem system);
   	boolean deleteById(Integer systemId);
    void update(ApplicationSystem system);
    void insert(ApplicationSystem system);

}
