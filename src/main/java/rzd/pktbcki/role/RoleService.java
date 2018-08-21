package rzd.pktbcki.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rzd.pktbcki.mapper.UserRoleMapper;
import rzd.pktbcki.user.UserRole;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * UserRole: VNikishin
 * Date: 26.06.18
 * Time: 18:14
 */
@Service
@Transactional
public class RoleService implements Serializable {


    /*--------------------------------------------
    |             C O N S T A N T S             |
    ============================================*/

    private static final long serialVersionUID = 1L;
/*
     используем статический logger, чтобы избежать вопросов сериализации
     static to optimize serialization
     protect- available to subclasses
        
*/
/*
for slf4j
private static final Logger logger = LoggerFactory.getLogger( UserRoleService.class );
*/



    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/
    @Autowired
    private UserRoleMapper roleMapper;

    @Value("${application.system.id}")
    private Integer systemId;

    @Value("${application.admin.role.name}")
    private String adminRoleName;

    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
    
    
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

    @Transactional(readOnly = true)
    public List<UserRole> findLoginRoles(Map map) {
        return roleMapper.findLoginRoles(map);
    }

    @Transactional(readOnly = true)
    public List<UserRole> findRolesForLogin(Map map) {
        map.put("systemId",systemId);
        map.put("adminRoleName",adminRoleName);
        return roleMapper.findRolesForLogin(map);
    }

    /**
     * Retrieve an {@link rzd.pktbcki.user.UserRole} from the data store by id.
     * @param id the id to search for
     * @return the {@link rzd.pktbcki.user.UserRole} if found
     */
    @Transactional(readOnly = true)
    public UserRole findById( Integer id){
        return  roleMapper.findById(id);
    }

    /**
     * Retrieve an {@link rzd.pktbcki.user.UserRole} from the data store by id.
     * @return the {@link rzd.pktbcki.user.UserRole} if found
     */
    @Transactional(readOnly = true)
    public List<UserRole> findAll(){
        UserRole userRole = new UserRole();
        userRole.setSystemId(systemId);
        userRole.setRoleName(adminRoleName);
        return  roleMapper.findAllRoles(userRole);
//        return  roleMapper.findAllRoles();
    }


    /**
     * Delete role.
     *
     * @param map the role_name,system_id
     */
    public void deleteRole(Map map) {
//        UserRole userRole=roleMapper.findById(id);
        map.put("systemId",systemId);
        roleMapper.deleteByRoleName(map);
//        roleMapper.deleteById(id);
    }

    /**
     * Delete role.
     *
     * @param map the user_name,systemId
     */
    public void unbindRoles(Map map) {
        map.put("systemId",systemId);
        roleMapper.deleteByUserName(map);
    }

    /**
     * bind role to login.
     *
     * @param map the user_name,systemId,roles
     */
    public void bindRoles(Map map, String[] selectedRoles) {
        map.put("systemId",systemId);
        Collection<UserRole> allRoles = findAll();
        roleMapper.deleteByUserName(map);
        for (int i = 0; i < selectedRoles.length; i++) {
            map.put("roleName",selectedRoles[i]);
            for (UserRole role : allRoles) {
                if (selectedRoles[i].equals(role.getRoleName())){
                    map.put("description",role.getDescription());
                }
            }
            roleMapper.insertLoginWithRole(map);

        }


    }

    /**
     * Delete role.
     *
     * @param id the role_id
     */
    public void deleteRole(Integer id) {
        roleMapper.deleteById(id);
    }


    /*
    * Insert role.
    *
    * @param role the UserRole account
    */
    @Transactional
    public void insertRole(UserRole role) {

/*
         Integer uniqueID = UUID.randomUUID().hashCode();
         role.setId(uniqueID);
*/
        role.setSystemId(systemId);
        role.setUserName("no user");
        roleMapper.insert(role);
    }


}
