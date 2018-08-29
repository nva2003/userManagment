package rzd.pktbcki.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rzd.pktbcki.mapper.LoginMapper;
import rzd.pktbcki.mapper.UserMapper;
import rzd.pktbcki.mapper.UserRoleMapper;
import rzd.pktbcki.system.UserCredentials;
import rzd.pktbcki.user.Login;
import rzd.pktbcki.user.UserRole;

import java.io.Serializable;

/**
 * Login: VNikishin
 * Date: 26.06.18
 * Time: 18:14
 */
@Service
@Transactional
public class LoginServiceImpl implements Serializable, LoginService {


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
    @Autowired
    private UserRoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;



    @Value("${application.system.id}")
    private Integer systemId;

    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
    
    
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/



    /**
     * Retrieve an {@link rzd.pktbcki.user.Login} from the data store by id.
     * @param id the id to search for
     * @return the {@link rzd.pktbcki.user.Login} if found
     */
    @Transactional(readOnly = true)
    public Login findById( Integer id){
        return  loginMapper.findById(id);
    }





    /**
      * Delete login.
      *
      * @param id the login
      */
    @Override
    public void deleteLogin(Integer id) {
        Login login = loginMapper.findById(id);
        UserRole userRole = new UserRole();
        userRole.setUserName(login.getUserName());
        userRole.setSystemId(login.getSystemId());
        roleMapper.deleteByLogin(userRole);
        loginMapper.deleteById(id);
    }


    /*
    * Insert login.
    *
    * @param login the Login account
    */
     @Override
     @Transactional
     public void insertLogin(Login login) {

         login.setSystemId(systemId);
         loginMapper.insert(login);

         //extend the duration of the user account
         if (login.getUser().getTimeBefore().before(login.getPasswordExpirationTime())) {
             login.getUser().setTimeBefore(login.getPasswordExpirationTime());
             login.getUser().setEditor(login.getEditor());
             login.getUser().setEditorIP(login.getCreatorIP());
             userMapper.updateUser(login.getUser());
         }
     }

        /*
        *
        * Update login.
        *
        * @param login the Login account
        */

     @Override
     @Transactional
     public void updateLogin(Login login) {
         login.setSystemId(systemId);
         login.setPasswordInitial(PasswordInitial.INSTALLED_ADMINISTRATOR.getValue());//Флаг: пароль является начальным (=установил администратор)
         login.setPasswordState(PasswordState.CHANGE_IS_NECESSARY.getValue());//Изменение пароля: необходимо / разрешено / невозможно

         loginMapper.update(login);
     }
        /*
        *
        * Update login.
        *
        * @param login the Login account
        */

     @Override
     @Transactional
     public void changePassword(Login login) {
         login.setSystemId(systemId);
         login.setPasswordState(PasswordState.POSSIBLE_TO_CHANGE.getValue());
         login.setPasswordInitial(PasswordInitial.IS_NOT_AN_INITIAL.getValue());
         loginMapper.changePassword(login);
     }

     @Override
     @Transactional
     public boolean isNeedToChangeThePassword(Integer loginId) {
//         login.setSystemId(systemId);
         Login login = loginMapper.findById(loginId);
         return PasswordState.CHANGE_IS_NECESSARY.getValue() == login.getPasswordState();
     }

     @Override
     @Transactional
     public boolean isNeedToChangeThePassword(String userName) {

         Login login = new Login();
         login.setUserName(userName);
         login.setSystemId(systemId);


         return PasswordState.CHANGE_IS_NECESSARY.getValue() == loginMapper.getPasswordState(login);
     }
}
