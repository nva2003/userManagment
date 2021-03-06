package rzd.pktbcki.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import rzd.pktbcki.user.EmailValidator;
import rzd.pktbcki.user.User;
import rzd.pktbcki.user.UserRole;
import rzd.pktbcki.user.UserService;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class UserRoleFormValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		return UserRole.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		UserRole group = (UserRole) target;


		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName", null, "не может быть пусто");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName", "NotEmpty.groupForm", "не может быть пусто");


	}

}