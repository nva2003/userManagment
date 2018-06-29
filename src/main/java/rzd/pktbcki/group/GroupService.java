package rzd.pktbcki.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rzd.pktbcki.mapper.UserRoleMapper;
import rzd.pktbcki.user.UserRole;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * UserRole: VNikishin
 * Date: 26.06.18
 * Time: 18:14
 */
@Service
@Transactional
public class GroupService implements Serializable {


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
    private UserRoleMapper groupMapper;


    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
    
    
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

    public List<UserRole> getGroupList() {
      return groupMapper.findAll();
    }

    /**
     * Retrieve an {@link rzd.pktbcki.user.UserRole} from the data store by id.
     * @param id the id to search for
     * @return the {@link rzd.pktbcki.user.UserRole} if found
     */
    @Transactional(readOnly = true)
    UserRole findById( Integer id){
        return  groupMapper.findById(id);
    }

    /**
     * Retrieve an {@link rzd.pktbcki.user.UserRole} from the data store by id.
     * @return the {@link rzd.pktbcki.user.UserRole} if found
     */
    @Transactional(readOnly = true)
    List<UserRole> findAll(){
        return  groupMapper.findAllRoles();
//        return  groupMapper.findAll();
    }


    /**
      * Delete user.
      *
      * @param roleName the role_name
      */
    public void deleteGroup(String roleName) {
//        UserRole userRole=groupMapper.findById(id);
        groupMapper.deleteByRoleName(roleName);
//        groupMapper.deleteById(id);
    }

    @Deprecated
    /**
      * Delete user.
      *
      * @param id the role_id
      */
    public void deleteGroup(Integer id) {
        groupMapper.deleteById(id);
    }


    /*
    * Insert group.
    *
    * @param group the UserRole account
    */
     @Transactional
     public void insertGroup(UserRole group) {

         Integer uniqueID = UUID.randomUUID().hashCode();
         group.setId(uniqueID);
         group.setUserName("no user");
       groupMapper.insert(group);
     }

        /*
        *
        * Update group.
        *
        * @param group the UserRole account
        */

     @Transactional
     public void updateGroup(UserRole group) {
         groupMapper.update(group);
     }
}
