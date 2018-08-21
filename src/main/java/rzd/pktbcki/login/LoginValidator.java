/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rzd.pktbcki.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rzd.pktbcki.user.EmailValidator;
import rzd.pktbcki.user.Login;

/**
 * <code>Validator</code> for <code>Pet</code> forms.
 * <p>
 * We're not using Bean Validation annotations here because it is easier to define such validation rule in Java.
 * </p>
 *
 */
@Component
public class LoginValidator implements Validator {

    private static final String REQUIRED = "Обязательно";

    @Autowired
    @Qualifier("passwordValidator")
    PasswordValidator passwordValidator;




    @Override
    public void validate(Object obj, Errors errors) {
        Login login = (Login) obj;
        String name = login.getUserName();
        // name validation
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("userName", REQUIRED, REQUIRED);
        }

        // password validation
        if (!StringUtils.hasLength(login.getPassword())) {
            errors.rejectValue("password", REQUIRED, REQUIRED);
        }

        if(!passwordValidator.valid(login.getPassword())){
      			errors.rejectValue("password", null, "пароль должен содержать не менее 8 символов");
      			errors.rejectValue("password", null, "пароль должен содержать символы как минимум 3-х видов из  следующих подмножеств: ");
      			errors.rejectValue("password", null, "A-Z (буквы в верхнем регистре)");
      			errors.rejectValue("password", null, "a-z (буквы в нижнем регистре)");
      			errors.rejectValue("password", null, "0-9 (цифры)");

      		}



        // userId date validation
        if (login.getUserId() == null) {
            errors.rejectValue("userId", REQUIRED, REQUIRED);
        }

/*
        // password matching validation
          if (!student.getPassword().equals(student.getConfirmPassword())) {
           errors.rejectValue("confirmPassword", "password.mismatch",
             "Password does not match");
          }
*/

    }

    /**
     * This Validator validates *just* Pet instances
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Login.class.isAssignableFrom(clazz);
    }


}
