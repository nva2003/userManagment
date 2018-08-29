package rzd.pktbcki.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rzd.pktbcki.login.LoginService;
import rzd.pktbcki.mapper.UserMapper;

import java.io.Serializable;
import java.util.*;

/**
 * User: VNikishin
 * Date: 26.06.18
 * Time: 18:14
 */
@Service
@Transactional
public class UserServiceImpl implements Serializable, UserService {


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
private static final Logger logger = LoggerFactory.getLogger( UserService.class );
*/


    
    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginService loginService;

    @Value("${application.system.id}")
    private Integer systemId;

    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
    
    
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

    @Override
    public List<User> getUserList() {
      return userMapper.findAll();
    }

    /**
     * Retrieve an {@link User} from the data store by id.
     * @param id the id to search for
     * @return the {@link User} if found
     */
    @Override
    @Transactional(readOnly = true)
    public User findById(Integer id){
        return  userMapper.findUserById(id);
    }

    /**
     * Retrieve an {@link User} from the data store by id.
     * @param id the id to search for
     * @return the {@link User} if found
     */
    @Override
    @Transactional(readOnly = true)
    public User findByIdWithLogin(Integer id){
//        User user =  userMapper.findUserByIdWithLoginAndRoles(id);
//        User user =  userMapper.findUserByIdWithLogin(id);
//        return user;
//        return  userMapper.findUserByIdWithLogin(id);
        Map map =  new HashMap();
        map.put("userId", id);
        map.put("systemId",systemId);
        return  userMapper.findUserByIdWithLoginAndRoles(map);
    }

    @Override
    public User findByIdWithLogin(Integer id, boolean isAllSystem){

        if (isAllSystem){
            Map map =  new HashMap();
            map.put("userId", id);
            return  userMapper.findUserByIdWithLoginAndRoles(map);
        } else {
            return this.findByIdWithLogin(id);
        }

    }

    @Override
    public List<User> getUser(User user) {
      return userMapper.searchUserList(user);
    }


    /**
      * Delete user.
      *
      * @param id the user_id
      */
    @Override
    public void deleteUser(Integer id) {
        User user = this.findByIdWithLogin(id, true);
        for (Login login : user.getLogins()) {
            loginService.deleteLogin(login.getId());
        }
        userMapper.deleteUser(id);

    }


    /*
    * Insert account.
    *
    * @param account the User account
    */
     @Override
     @Transactional
     public User insertUser(User user) {

       userMapper.insertUser(user);
         return userMapper.getUser(user);
     }

        /*
        *
        * Update account.
        *
        * @param account the User account
        */

     @Override
     @Transactional
     public void updateUser(User user) {
         userMapper.updateUser(user);

         User userWithLogin = this.findByIdWithLogin(user.getId());
         for (Login login : userWithLogin.getLogins()) {
             login.setPasswordExpirationTime(user.getTimeBefore());
             login.setEditor(user.getEditor());
             login.setEditorIP(user.getEditorIP());
             loginService.updateLogin(login);
         }


     }
}
