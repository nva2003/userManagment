package rzd.pktbcki.login;

import org.springframework.transaction.annotation.Transactional;
import rzd.pktbcki.system.UserCredentials;
import rzd.pktbcki.user.Login;

/**
 * User: VNikishin
 * Date: 22.08.18
 * Time: 16:11
 */
public interface LoginService {


    void deleteLogin(Integer id);

    public Login findById( Integer id);
    /*
        * Insert login.
        *
        * @param login the Login account
        */
     @Transactional
     void insertLogin(Login login);

    @Transactional
    void updateLogin(Login login);

    @Transactional
    void changePassword(Login login);

    @Transactional
    boolean isNeedToChangeThePassword(Integer loginId);

    public boolean isNeedToChangeThePassword(String userName);
}
