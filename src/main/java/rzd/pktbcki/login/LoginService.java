package rzd.pktbcki.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rzd.pktbcki.mapper.LoginMapper;
import rzd.pktbcki.mapper.LoginMapper;
import rzd.pktbcki.user.Login;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Login: VNikishin
 * Date: 26.06.18
 * Time: 18:14
 */
@Service
@Transactional
public class LoginService implements Serializable {


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
private static final Logger logger = LoggerFactory.getLogger( LoginService.class );
*/


    
    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/
    @Autowired
    private LoginMapper loginMapper;


    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
    
    
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

    public List<Login> getLoginList() {
      return loginMapper.findAll();
    }

    /**
     * Retrieve an {@link rzd.pktbcki.user.Login} from the data store by id.
     * @param id the id to search for
     * @return the {@link rzd.pktbcki.user.Login} if found
     */
    @Transactional(readOnly = true)
    Login findById( Integer id){
        return  loginMapper.findById(id);
    }

    /**
     * Retrieve an {@link rzd.pktbcki.user.Login} from the data store by id.
     * @return the {@link rzd.pktbcki.user.Login} if found
     */
    @Transactional(readOnly = true)
    List<Login> findAll(){
        return  loginMapper.findAll();
//        return  loginMapper.findAll();
    }




    /**
      * Delete user.
      *
      * @param id the role_id
      */
    public void deleteLogin(Integer id) {
        loginMapper.deleteById(id);
    }


    /*
    * Insert login.
    *
    * @param login the Login account
    */
     @Transactional
     public void insertLogin(Login login) {

         Integer uniqueID = UUID.randomUUID().hashCode();
         login.setId(uniqueID);
         login.setUserName("no user");
       loginMapper.insert(login);
     }

        /*
        *
        * Update login.
        *
        * @param login the Login account
        */

     @Transactional
     public void updateLogin(Login login) {
         loginMapper.update(login);
     }
}
