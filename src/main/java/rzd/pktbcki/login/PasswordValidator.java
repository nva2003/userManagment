package rzd.pktbcki.login;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("passwordValidator")
public class PasswordValidator {

	private Pattern pattern;
	private Matcher matcher;


    /*
    *
    *  Minimum eight characters, at least one letter and one number:

    "^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"

    Minimum eight characters, at least one letter, one number and one special character:

    "^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$"

    Minimum eight characters, at least one uppercase letter, one lowercase letter and one number:

    "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"

    Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character:

    "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}"

    Minimum eight and maximum 10 characters, at least one uppercase letter, one lowercase letter, one number and one special character:

    "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,10}"
    *
    *   ^                 # start-of-string
    (?=.*[0-9])       # a digit must occur at least once
    (?=.*[a-z])       # a lower case letter must occur at least once
    (?=.*[A-Z])       # an upper case letter must occur at least once
    (?=.*[@#$%^&+=])  # a special character must occur at least once
    (?=\S+$)          # no whitespace allowed in the entire string
    .{8,}             # anything, at least eight places though
    $                 # end-of-string
    * */
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}";

	public PasswordValidator() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

	public boolean valid(final String password) {

		matcher = pattern.matcher(password);
		return matcher.matches();

	}
}