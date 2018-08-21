package rzd.pktbcki.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import rzd.pktbcki.user.User;

import java.util.List;
import java.util.Map;

/**
 * User: VNikishin
 * Date: 26.06.18
 * Time: 18:13
 */
public interface UserMapper {
    User findUserById(Integer userId);
    User findUserByIdWithLoginAndRoles(Map map);
   	List<User> findAll();
   	boolean deleteUser(Integer id);
    void updateUser(User user);
    void insertUser(User user);
    User getUser(User user);
    List<User> searchUserList(User user);
    List<User> searchUserList(Map map);
}
