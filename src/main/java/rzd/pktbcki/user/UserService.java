package rzd.pktbcki.user;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: VNikishin
 * Date: 22.08.18
 * Time: 16:23
 */
public interface UserService {
    List<User> getUserList();

    @Transactional(readOnly = true)
    User findById(Integer id);

    @Transactional(readOnly = true)
    User findByIdWithLogin(Integer id);

    User findByIdWithLogin(Integer id, boolean isAllSystem);

    List<User> getUser(User user);

    void deleteUser(Integer id);

    /*
        * Insert account.
        *
        * @param account the User account
        */
     @Transactional
     User insertUser(User user);

    @Transactional
    void updateUser(User user);
}
