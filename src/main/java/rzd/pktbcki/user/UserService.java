package rzd.pktbcki.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rzd.pktbcki.mapper.UserMapper;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * User: VNikishin
 * Date: 26.06.18
 * Time: 18:14
 */
@Service
@Transactional
public class UserService implements Serializable {


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


    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
    
    
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

    public List<User> getUserList() {
      return userMapper.findAll();
    }

    /**
     * Retrieve an {@link User} from the data store by id.
     * @param id the id to search for
     * @return the {@link User} if found
     */
    @Transactional(readOnly = true)
    public User findById( Integer id){
        return  userMapper.findUserById(id);
    }

    public List<User> getUser(User user) {
      return userMapper.searchUserList(user);
    }

    public List<User> getUser(Map map) {
      return userMapper.searchUserList(map);
    }

    /**
      * Delete user.
      *
      * @param id the user_id
      */
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }


    /*
    * Insert account.
    *
    * @param account the User account
    */
     @Transactional
     public void insertUser(User user) {

         Integer uniqueID = UUID.randomUUID().hashCode();
//         Integer uniqueID = UUID.randomUUID().clockSequence();


         user.setId(uniqueID);
         user.setIdAdminChanged(uniqueID);
         user.setIdAdminCreated(uniqueID);

       userMapper.insertUser(user);
//       loginMapper.insertProfile(user);
//       userRoleMapper.insertSignon(user);
     }

        /*
        *
        * Update account.
        *
        * @param account the User account
        */

     @Transactional
     public void updateUser(User user) {
         userMapper.updateUser(user);
//       loginMapper.updateProfile(user);

/*
       if (user.getPassword() != null && user.getPassword().length() > 0) {
         accountMapper.updateSignon(user);
       }
*/
     }
}
