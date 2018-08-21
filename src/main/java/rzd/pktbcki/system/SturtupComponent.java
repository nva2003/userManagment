package rzd.pktbcki.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rzd.pktbcki.user.Login;
import rzd.pktbcki.user.UserRole;

import java.io.Serializable;

/**
 * User: VNikishin
 * Date: 10.08.18
 * Time: 16:42
 */
@Component
public class SturtupComponent implements Serializable {


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
private static final Logger logger = LoggerFactory.getLogger( SturtupComponent.class );
*/
    
    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/
    @Value("${application.system.id}")
    private Integer systemId;

    @Value("${application.system.name}")
    private String systemName;

    @Value("${application.system.audit.id}")
    private Integer auditId;


    @Value("${application.admin.login}")
    private String adminLogin;

    @Value("${application.admin.pwd}")
    private String adminPassword;

    @Value("${application.admin.role.name}")
    private String adminRoleName;

    @Value("${application.admin.role.description}")
    private String adminRoleDescription;


    @Autowired
    private SystemService systemService;


    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/

//    @EventListener(ContextStartedEvent.class)
    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        ApplicationSystem system = new ApplicationSystem();
        system.setAuditId(auditId);
        system.setSystemId(systemId);
        system.setSystemName(systemName);

        Login login = new Login();
        login.setUserName(adminLogin);
        login.setPassword(adminPassword);
        login.setSystemId(systemId);
        login.setCreator("init");
        login.setEditor("init");
        login.setCreatorIP("init");
        login.setEditorIP("init");
        login.setUserId(-1);
        login.setPasswordState(0);

        UserRole userRole = new UserRole();
        userRole.setRoleName(adminRoleName);
        userRole.setUserName(adminLogin);
        userRole.setDescription(adminRoleDescription);
        userRole.setSystemId(systemId);
        userRole.setCreator("init");
        userRole.setEditor("init");
        userRole.setCreatorIP("init");
        userRole.setEditorIP("init");

        //check existing system,admin,role
        systemService.initSystem(system);
        systemService.initAdmin(login, userRole);

    }

    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/
}
