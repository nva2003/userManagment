package rzd.pktbcki.user;

import java.io.Serializable;

/**
 * User: VNikishin
 * Date: 21.06.18
 * Time: 13:06
 */
public class System extends BaseEntity {


    /*--------------------------------------------
    |             C O N S T A N T S             |
    ============================================*/

    private static final long serialVersionUID = 1L;

    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/

    private Integer sistemId;
    private Integer loginId;
    private String systemName;
    private String description;


    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
    
    
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

    public Integer getSistemId() {
        return sistemId;
    }

    public void setSistemId(Integer sistemId) {
        this.sistemId = sistemId;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
