package rzd.pktbcki.mapper;

import rzd.pktbcki.user.User;
import rzd.pktbcki.user.UserRole;

import java.util.List;
import java.util.Map;

/**
 * User: VNikishin
 * Date: 26.06.18
 * Time: 18:13
 */
public interface UserRoleMapper {
    UserRole findById(Integer GroupId);
//    UserRole findByRoleName(String roleName);
    List<UserRole> findUserRoleByLogin(String Login);
   	List<UserRole> findAll();
   	List<UserRole> findAllRoles();
    @Deprecated
   	boolean deleteById(Integer id);
   	boolean deleteByRoleName(String roleName);
   	long count();
    void update(UserRole Group);
    void insert(UserRole Group);
}
