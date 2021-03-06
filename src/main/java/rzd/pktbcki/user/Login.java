package rzd.pktbcki.user;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

/**
 * User: VNikishin
 * Date: 21.06.18
 * Time: 10:45
 */
public class Login extends BaseEntity{


    /*--------------------------------------------
    |             C O N S T A N T S             |
    ============================================*/

    private static final long serialVersionUID = 1L;

    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/

    private String userName;
    private String password;
    private Integer userId;
    private Integer systemId;
    //    Флаг: пароль является начальным (=установил администратор)
    private String passwordInitial;
    //Дата: пароль установлен администратором заново
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp passwordDateSet;
    //Изменение пароля: необходимо / разрешено / невозможно
    private Integer passwordState;
    //Время действия логина
    private Timestamp passwordExpirationTime;
    private String creator;
    private String editor;
    private String creatorIP;
    private String editorIP;

    private Set<UserRole> roles;

    private User user;

    private String confirmPassword;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
    
    
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPasswordInitial() {
        return passwordInitial;
    }

    public void setPasswordInitial(String passwordInitial) {
        this.passwordInitial = passwordInitial;
    }

    public Integer getPasswordState() {
        return passwordState;
    }

    public void setPasswordState(Integer passwordState) {
        this.passwordState = passwordState;
    }

    public Timestamp getPasswordDateSet() {
        return passwordDateSet;
    }

    public void setPasswordDateSet(Timestamp passwordDateSet) {
        this.passwordDateSet = passwordDateSet;
    }

    public Timestamp getPasswordExpirationTime() {
        return passwordExpirationTime;
    }

    public void setPasswordExpirationTime(Timestamp passwordExpirationTime) {
        this.passwordExpirationTime = passwordExpirationTime;
    }

    public void setPasswordExpirationTime(String passwordExpirationTime) throws ParseException {
        this.passwordExpirationTime = new java.sql.Timestamp(sdf.parse(passwordExpirationTime).getTime());
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Login)) return false;

        Login login = (Login) o;

        if (creator != null ? !creator.equals(login.creator) : login.creator != null) return false;
        if (creatorIP != null ? !creatorIP.equals(login.creatorIP) : login.creatorIP != null) return false;
        if (editor != null ? !editor.equals(login.editor) : login.editor != null) return false;
        if (editorIP != null ? !editorIP.equals(login.editorIP) : login.editorIP != null) return false;
        if (password != null ? !password.equals(login.password) : login.password != null) return false;
        if (passwordDateSet != null ? !passwordDateSet.equals(login.passwordDateSet) : login.passwordDateSet != null)
            return false;
        if (passwordExpirationTime != null ? !passwordExpirationTime.equals(login.passwordExpirationTime) : login.passwordExpirationTime != null)
            return false;
        if (passwordInitial != null ? !passwordInitial.equals(login.passwordInitial) : login.passwordInitial != null)
            return false;
        if (passwordState != null ? !passwordState.equals(login.passwordState) : login.passwordState != null)
            return false;
        if (systemId != null ? !systemId.equals(login.systemId) : login.systemId != null) return false;
        if (userId != null ? !userId.equals(login.userId) : login.userId != null) return false;
        if (userName != null ? !userName.equals(login.userName) : login.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (systemId != null ? systemId.hashCode() : 0);
        result = 31 * result + (passwordInitial != null ? passwordInitial.hashCode() : 0);
        result = 31 * result + (passwordDateSet != null ? passwordDateSet.hashCode() : 0);
        result = 31 * result + (passwordState != null ? passwordState.hashCode() : 0);
        result = 31 * result + (passwordExpirationTime != null ? passwordExpirationTime.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (editor != null ? editor.hashCode() : 0);
        result = 31 * result + (creatorIP != null ? creatorIP.hashCode() : 0);
        result = 31 * result + (editorIP != null ? editorIP.hashCode() : 0);
        return result;
    }
}
