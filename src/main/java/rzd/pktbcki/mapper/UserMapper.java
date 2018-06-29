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
    User getUser(Integer userId);
    User findUserById(Integer userId);
//    User findOne(Integer id);
   	List<User> findAll();
   	boolean exists(Integer id);
   	boolean deleteUser(Integer id);
   	long count();
    void updateUser(User user);
    void insertUser(User user);
    List<User> searchUserList(User user);
    List<User> searchUserList(Map map);
}
