package rzd.pktbcki.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class UserFormValidator implements Validator {

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		User user = (User) target;


//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", null, "не может быть пусто");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.userForm", "не может быть пусто");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.userForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName","NotEmpty.userForm", "не может быть пусто");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "patronymicName", "NotEmpty.userForm", "не может быть пусто");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm", "не может быть пусто");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.userForm", "не может быть пусто");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","NotEmpty.userForm.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "company", "NotEmpty.userForm", "не может быть пусто");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "department", "NotEmpty.userForm", "не может быть пусто");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.userForm", "не может быть пусто");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeBefore", "NotEmpty.userForm", "не может быть пусто");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ip", "NotEmpty.userForm", "не может быть пусто");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numberASOZ", "NotEmpty.userForm", "не может быть пусто");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inputUserId", "NotEmpty.userForm", "не может быть пусто");

		if(!emailValidator.valid(user.getEmail())){
			errors.rejectValue("email", "Pattern.userForm.email");
		}
		
/*
		if(user.getNumber()==null || user.getNumber()<=0){
			errors.rejectValue("number", "NotEmpty.userForm.number");
		}
		
		if(user.getCountry().equalsIgnoreCase("none")){
			errors.rejectValue("country", "NotEmpty.userForm.country");
		}
		
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Diff.userform.confirmPassword");
		}
		
		if (user.getFramework() == null || user.getFramework().size() < 2) {
			errors.rejectValue("framework", "Valid.userForm.framework");
		}

		if (user.getSkill() == null || user.getSkill().size() < 3) {
			errors.rejectValue("skill", "Valid.userForm.skill");
		}
*/

	}

}