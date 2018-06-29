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
    Login getLogin(Integer userId);
    Login findById(Integer userId);
//    Login findOne(Integer id);
   	List<Login> findAll();
   	boolean exists(Integer id);
   	boolean deleteById(Integer id);
   	long count();
    List<Login> searchLoginList(Login user);
    List<Login> searchLoginList(Map map);
    void update(Login login);
    void insert(Login login);

}
