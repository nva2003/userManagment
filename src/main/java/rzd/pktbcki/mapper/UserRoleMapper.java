package rzd.pktbcki.mapper;

import rzd.pktbcki.user.Login;
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
    UserRole findById(Integer RoleId);
   	List<UserRole> findLoginRoles(Map map);
   	List<UserRole> findRole(UserRole userRole);
   	List<UserRole> findRolesForLogin(Map map);
   	List<UserRole> findAllRoles(UserRole userRole);
   	boolean deleteById(Integer id);
   	boolean deleteByLogin(UserRole userRole);
   	boolean deleteByRoleName(Map map);
   	boolean deleteByUserName(Map map);
    void insert(UserRole Role);
    void insertLoginWithRole(Map map);
}
