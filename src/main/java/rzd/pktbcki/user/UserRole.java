package rzd.pktbcki.user;

import java.io.Serializable;

/**
 * User: VNikishin
 * Date: 21.06.18
 * Time: 10:50
 */
public class UserRole extends BaseEntity {


    /*--------------------------------------------
    |             C O N S T A N T S             |
    ============================================*/

    private static final long serialVersionUID = 1L;

    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/

    private Integer systemId;
    private String userName;
    private String roleName;
    private String description;
    private String creator;
    private String editor;
    private String creatorIP;
    private String editorIP;


    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
    
    
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/


    public boolean isBinded() {
        return userName!=null;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getCreatorIP() {
        return creatorIP;
    }

    public void setCreatorIP(String creatorIP) {
        this.creatorIP = creatorIP;
    }

    public String getEditorIP() {
        return editorIP;
    }

    public void setEditorIP(String editorIP) {
        this.editorIP = editorIP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;

        UserRole userRole = (UserRole) o;

        if (description != null ? !description.equals(userRole.description) : userRole.description != null)
            return false;
        if (roleName != null ? !roleName.equals(userRole.roleName) : userRole.roleName != null) return false;
        if (systemId != null ? !systemId.equals(userRole.systemId) : userRole.systemId != null) return false;
        if (userName != null ? !userName.equals(userRole.userName) : userRole.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = systemId != null ? systemId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
