package rzd.pktbcki.mapper;

import rzd.pktbcki.user.Login;

import java.util.List;
import java.util.Map;

/**
 * Login: VNikishin
 * Date: 26.06.18
 * Time: 18:13
 */
public interface LoginMapper {
    Login findById(Integer userId);
   	List<Login> findAll();
   	boolean deleteById(Integer id);
    List<Login> searchLoginList(Login user);
    Integer getPasswordState(Login user);
    void update(Login login);
    void changePassword(Login login);
    void insert(Login login);

}
