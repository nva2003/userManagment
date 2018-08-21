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
package rzd.pktbcki.system;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import rzd.pktbcki.user.Login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <code>Validator</code> for <code>Pet</code> forms.
 * <p>
 * We're not using Bean Validation annotations here because it is easier to define such validation rule in Java.
 * </p>
 *
 * @author V.N.
 */
public class ApplicationSystemValidator implements Validator {

    private static final String REQUIRED = "Обязательно";

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String ID_PATTERN = "[0-9]+";
    private static final String STRING_PATTERN = "[a-zA-Z]+";
    private static final String MOBILE_PATTERN = "[0-9]{10}";

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void validate(Object obj, Errors errors) {
        ApplicationSystem applicationSystem = (ApplicationSystem) obj;

        // AuditId validation
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "auditId", "required.auditId",
                REQUIRED);

        String name = String.valueOf(applicationSystem.getAuditId());


        // input string conatains numeric values only
        if (applicationSystem.getAuditId() != null) {
            pattern = Pattern.compile(ID_PATTERN);
            matcher = pattern.matcher(applicationSystem.getAuditId().toString());
            if (!matcher.matches()) {
                errors.rejectValue("auditId", "auditId.incorrect",
                        "Enter a numeric value");
            }
        }

    }

    /**
     * This Validator validates *just* Pet instances
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return ApplicationSystem.class.isAssignableFrom(clazz);
    }


}
