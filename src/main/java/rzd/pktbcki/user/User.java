package rzd.pktbcki.user;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * User: VNikishin
 * Date: 20.06.18
 * Time: 18:01
 */
public class User extends BaseEntity {


    /*--------------------------------------------
    |             C O N S T A N T S             |
    ============================================*/

    private static final long serialVersionUID = 1L;



    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/

    @NotEmpty
    private String inputUserId;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String patronymicName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String company;
    @NotEmpty
    private String department;
    @NotEmpty
    private String title;

    @NotEmpty
    private Timestamp timeBefore;
    @NotEmpty
    private String ip;
    @NotEmpty
    private String numberASOZ;
    private Timestamp whenChanged;
    private Timestamp whenCreated;
    private String creator;
    private String editor;
    private String creatorIP;
    private String editorIP;

    private Set<Login> logins;

    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
    
    
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

    protected Set<Login> getLoginsInternal() {
        if (this.logins == null) {
            this.logins = new HashSet<>();
        }
        return this.logins;
    }

    public void addLogin(Login login) {
        if (login.isNew()) {
            getLoginsInternal().add(login);
        }
        login.setUser(this);
    }

    /**
     * Return the Login with the given name, or null if none found for this User.
     *
     * @param name to test
     * @return true if login name is already in use
     */
    public Login getLogin(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Login login : getLoginsInternal()) {
            if (!ignoreNew || !login.isNew()) {
                String compName = login.getUserName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return login;
                }
            }
        }
        return null;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getTimeBefore() {
        return timeBefore;
    }

    public void setTimeBefore(Timestamp timeBefore) {
        this.timeBefore = timeBefore;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNumberASOZ() {
        return numberASOZ;
    }

    public void setNumberASOZ(String numberASOZ) {
        this.numberASOZ = numberASOZ;
    }

    public Timestamp getWhenChanged() {
        return whenChanged;
    }

    public void setWhenChanged(Timestamp whenChanged) {
        this.whenChanged = whenChanged;
    }

    public Timestamp getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Timestamp whenCreated) {
        this.whenCreated = whenCreated;
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

    public String getInputUserId() {
        return inputUserId;
    }

    public void setInputUserId(String inputUserId) {
        this.inputUserId = inputUserId;
    }

    public Set<Login> getLogins() {
        return logins;
    }

    public void setLogins(Set<Login> logins) {
        this.logins = logins;
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
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (company != null ? !company.equals(user.company) : user.company != null) return false;
        if (creator != null ? !creator.equals(user.creator) : user.creator != null) return false;
        if (department != null ? !department.equals(user.department) : user.department != null) return false;
        if (editor != null ? !editor.equals(user.editor) : user.editor != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (inputUserId != null ? !inputUserId.equals(user.inputUserId) : user.inputUserId != null) return false;
        if (ip != null ? !ip.equals(user.ip) : user.ip != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (logins != null ? !logins.equals(user.logins) : user.logins != null) return false;
        if (numberASOZ != null ? !numberASOZ.equals(user.numberASOZ) : user.numberASOZ != null) return false;
        if (patronymicName != null ? !patronymicName.equals(user.patronymicName) : user.patronymicName != null)
            return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (timeBefore != null ? !timeBefore.equals(user.timeBefore) : user.timeBefore != null) return false;
        if (title != null ? !title.equals(user.title) : user.title != null) return false;
        if (whenChanged != null ? !whenChanged.equals(user.whenChanged) : user.whenChanged != null) return false;
        if (whenCreated != null ? !whenCreated.equals(user.whenCreated) : user.whenCreated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = inputUserId != null ? inputUserId.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (patronymicName != null ? patronymicName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (timeBefore != null ? timeBefore.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (numberASOZ != null ? numberASOZ.hashCode() : 0);
        result = 31 * result + (whenChanged != null ? whenChanged.hashCode() : 0);
        result = 31 * result + (whenCreated != null ? whenCreated.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (editor != null ? editor.hashCode() : 0);
        result = 31 * result + (logins != null ? logins.hashCode() : 0);
        return result;
    }
}
