package rzd.pktbcki.system;

import org.springframework.transaction.annotation.Transactional;
import rzd.pktbcki.user.Login;
import rzd.pktbcki.user.UserRole;

/**
 * User: VNikishin
 * Date: 22.08.18
 * Time: 16:40
 */
public interface SystemService {

    ApplicationSystem findById( Integer id);

    void deleteSystem(Integer id);

    /*
        * Insert system.
        *
        * @param system the System system
        */
     @Transactional
     void insertSystem(ApplicationSystem system);

    @Transactional
    void updateSystem(ApplicationSystem system);

    @Transactional
    void initSystem(ApplicationSystem system);

    @Transactional
    void initAdmin(Login login, UserRole userRole);
}
