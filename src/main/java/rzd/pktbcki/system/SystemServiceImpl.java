package rzd.pktbcki.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rzd.pktbcki.mapper.LoginMapper;
import rzd.pktbcki.mapper.SystemMapper;
import rzd.pktbcki.mapper.UserRoleMapper;
import rzd.pktbcki.user.Login;
import rzd.pktbcki.user.UserRole;

import java.io.Serializable;

/**
 * System: VNikishin
 * Date: 26.06.18
 * Time: 18:14
 */
@Service
@Transactional
public class SystemServiceImpl implements Serializable, SystemService {


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
private static final Logger logger = LoggerFactory.getLogger( SystemService.class );
*/


    
    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private SystemMapper systemMapper;
    @Autowired
    private UserRoleMapper roleMapper;



    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
    private static Integer EXIST = 1;
    
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/


    /**
     * Retrieve an {@link ApplicationSystem} from the data store by id.
     * @param id the id to search for
     * @return the {@link ApplicationSystem} if found
     */
    @Transactional(readOnly = true)
    public ApplicationSystem findById( Integer id){
        return  systemMapper.findById(id);
    }


    /**
      * Delete system.
      *
      * @param id the system
      */
    @Override
    public void deleteSystem(Integer id) {
        systemMapper.deleteById(id);
    }


    /*
    * Insert system.
    *
    * @param system the System system
    */
     @Override
     @Transactional
     public void insertSystem(ApplicationSystem system) {
       systemMapper.insert(system);
     }

        /*
        *
        * Update system.
        *
        * @param system the System account
        */

     @Override
     @Transactional
     public void updateSystem(ApplicationSystem system) {
         systemMapper.update(system);
     }

     @Override
     @Transactional
     public void initSystem(ApplicationSystem system) {

         if(systemMapper.isExist(system) == null){
             systemMapper.insert(system);
         }
     }

     @Override
     @Transactional
     public void initAdmin(Login login, UserRole userRole) {

         if(loginMapper.searchLoginList(login).isEmpty()){
             loginMapper.insert(login);
         }
         if(roleMapper.findRole(userRole).isEmpty()){
             roleMapper.insert(userRole);
         }
     }
}
