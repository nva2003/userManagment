package rzd.pktbcki.user;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
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
    //    Флаг: пароль является начальным (=установил администратор)
    private boolean passwordInitial;
    //Дата: пароль установлен администратором заново
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String passwordDateSet;
    //Изменение пароля: необходимо / разрешено / невозможно
    private Integer passwordState;
    //Время действия логина
    private String timeBefore;

    private Set<UserRole> roles;

    private User user;
    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
    
    
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public boolean isPasswordInitial() {
        return passwordInitial;
    }

    public void setPasswordInitial(boolean passwordInitial) {
        this.passwordInitial = passwordInitial;
    }

    public String getPasswordDateSet() {
        return passwordDateSet;
    }

    public void setPasswordDateSet(String passwordDateSet) {
        this.passwordDateSet = passwordDateSet;
    }

    public Integer getPasswordState() {
        return passwordState;
    }

    public void setPasswordState(Integer passwordState) {
        this.passwordState = passwordState;
    }

    public String getTimeBefore() {
        return timeBefore;
    }

    public void setTimeBefore(String timeBefore) {
        this.timeBefore = timeBefore;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Login)) return false;

        Login login = (Login) o;

        if (passwordInitial != login.passwordInitial) return false;
        if (password != null ? !password.equals(login.password) : login.password != null) return false;
        if (passwordDateSet != null ? !passwordDateSet.equals(login.passwordDateSet) : login.passwordDateSet != null)
            return false;
        if (passwordState != null ? !passwordState.equals(login.passwordState) : login.passwordState != null)
            return false;
        if (roles != null ? !roles.equals(login.roles) : login.roles != null) return false;
        if (timeBefore != null ? !timeBefore.equals(login.timeBefore) : login.timeBefore != null) return false;
        if (user != null ? !user.equals(login.user) : login.user != null) return false;
        if (userId != null ? !userId.equals(login.userId) : login.userId != null) return false;
        if (userName != null ? !userName.equals(login.userName) : login.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (passwordInitial ? 1 : 0);
        result = 31 * result + (passwordDateSet != null ? passwordDateSet.hashCode() : 0);
        result = 31 * result + (passwordState != null ? passwordState.hashCode() : 0);
        result = 31 * result + (timeBefore != null ? timeBefore.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
