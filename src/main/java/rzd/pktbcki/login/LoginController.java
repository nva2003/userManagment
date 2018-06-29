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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import rzd.pktbcki.user.Login;
import rzd.pktbcki.user.User;
import rzd.pktbcki.user.UserService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
@RequestMapping("/users/{userId}")
class LoginController {

    private static final String VIEWS_LOGINS_CREATE_OR_UPDATE_FORM = "logins/createOrUpdateLoginForm";
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;



    @ModelAttribute("user")
    public User findUser(@PathVariable("userId") int userId) {
        return this.userService.findById(userId);
    }

    @InitBinder("user")
    public void initUserBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("login")
    public void initLoginBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new LoginValidator());
    }

    @GetMapping("/logins/new")
    public String initCreationForm(User user, ModelMap model) {
        Login login = new Login();
        user.addLogin(login);
        model.put("login", login);
        return VIEWS_LOGINS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/logins/new")
    public String processCreationForm(User user, @Valid Login login, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(login.getUserName()) && login.isNew() && user.getLogin(login.getUserName(), true) != null){
            result.rejectValue("userName", "duplicate", "already exists");
        }
        user.addLogin(login);
        if (result.hasErrors()) {
            model.put("login", login);
            return VIEWS_LOGINS_CREATE_OR_UPDATE_FORM;
        } else {
            this.loginService.insertLogin(login);
            return "redirect:/users/{userId}";
        }
    }

/*
    @GetMapping("/logins/{loginId}/edit")
    public String initUpdateForm(@PathVariable("loginId") int loginId, ModelMap model) {
        Login login = this.loginService.findById(loginId);
        model.put("login", login);
        return VIEWS_LOGINS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/logins/{loginId}/edit")
    public String processUpdateForm(@Valid Login login, BindingResult result, User user, ModelMap model) {
        if (result.hasErrors()) {
            login.setUser(user);
            model.put("login", login);
            return VIEWS_LOGINS_CREATE_OR_UPDATE_FORM;
        } else {
            user.addLogin(login);
            this.loginService.save(login);
            return "redirect:/users/{userId}";
        }
    }
*/

}
