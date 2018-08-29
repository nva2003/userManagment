package rzd.pktbcki.role;

import org.springframework.transaction.annotation.Transactional;
import rzd.pktbcki.user.UserRole;

import java.util.List;
import java.util.Map;

/**
 * User: VNikishin
 * Date: 22.08.18
 * Time: 16:22
 */
public interface RoleService {
    @Transactional(readOnly = true)
    List<UserRole> findLoginRoles(Map map);

    @Transactional(readOnly = true)
    List<UserRole> findRolesForLogin(Map map);

    @Transactional(readOnly = true)
    UserRole findById(Integer id);

    @Transactional(readOnly = true)
    List<UserRole> findAll();

    void deleteRole(Map map);

    void unbindRoles(Map map);

    void bindRoles(Map map, String[] selectedRoles);

    void deleteRole(Integer id);

    /*
        * Insert role.
        *
        * @param role the UserRole account
        */
    @Transactional
    void insertRole(UserRole role);
}
