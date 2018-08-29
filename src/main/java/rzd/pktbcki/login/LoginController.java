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

import com.mifmif.common.regex.Generex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import rzd.pktbcki.role.RoleService;
import rzd.pktbcki.system.HttpServletRequestUtil;
import rzd.pktbcki.user.Login;
import rzd.pktbcki.user.User;
import rzd.pktbcki.user.UserRole;
import rzd.pktbcki.user.UserService;
import rzd.pktbcki.util.StringToTimestampPropertyEditorSupport;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author I am
 */
@Controller
@RequestMapping("/users/{userId}")
class LoginController {

    private static final String VIEWS_LOGINS_CREATE_FORM = "logins/createLoginForm";
    private static final String VIEWS_LOGINS_UPDATE_FORM = "logins/updateLoginForm";
    private static final String ROLE_BINDING_LIST = "roles/loginInRoles";
    private static final String USER_DETAILS = "users/userDetails";
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private LoginValidator loginValidator;



    @ModelAttribute("user")
    public User findUser(@PathVariable("userId") int userId) {
//        return this.userService.findById(userId);
        return this.userService.findByIdWithLogin(userId);
    }

    @InitBinder("user")
    public void initUserBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("login")
    public void initLoginBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(loginValidator);
        dataBinder.registerCustomEditor(Timestamp.class, "passwordExpirationTime",  new StringToTimestampPropertyEditorSupport());
    }

    @GetMapping("/logins/new")
    public String initCreationForm(User user, ModelMap model) {
        model.put("menu", "user list");
        Login login = new Login();
        login.setUserName(user.getInputUserId());

        Generex generex = new Generex("([0-9])([a-zA-Z])([a-zA-Z0-9]{8,15})");
        String randomStr = generex.random();

        login.setPassword(randomStr);

        user.addLogin(login);
        model.put("login", login);
        return VIEWS_LOGINS_CREATE_FORM;
    }

    @PostMapping("/logins/new")
    public String processCreationForm(User user, @Valid Login login, BindingResult result, ModelMap model, HttpServletRequest request) {
        if (StringUtils.hasLength(login.getUserName()) && login.isNew() && user.getLogin(login.getUserName(), true) != null){
            result.rejectValue("userName", "duplicate", "already exists");
        }
        user.addLogin(login);
        if (result.hasErrors()) {
            model.put("login", login);
            model.put("menu", "user list");
            return VIEWS_LOGINS_CREATE_FORM;
        } else {
            login.setPasswordInitial(PasswordInitial.INSTALLED_ADMINISTRATOR.getValue());//Флаг: пароль является начальным (=установил администратор)
            login.setPasswordState(PasswordState.CHANGE_IS_NECESSARY.getValue());//Изменение пароля: необходимо / разрешено / невозможно
            login.setCreator(HttpServletRequestUtil.getLogin(request));
            login.setEditor(HttpServletRequestUtil.getLogin(request));
            login.setCreatorIP(HttpServletRequestUtil.getClientIp(request));
            login.setEditorIP(HttpServletRequestUtil.getClientIp(request));

            this.loginService.insertLogin(login);
            return "redirect:/users/{userId}";
        }
    }

    @GetMapping("/logins/{loginId}/edit")
    public String initUpdateForm(@PathVariable("loginId") int loginId, ModelMap model, User user) {
        model.put("menu", "user list");
        Login login = this.loginService.findById(loginId);
        login.setUser(user);
        model.put("login", login);
        return VIEWS_LOGINS_UPDATE_FORM;
    }

    @PostMapping("/logins/{loginId}/edit")
    public String processUpdateForm(@Valid Login login, BindingResult result, User user, ModelMap model, HttpServletRequest request) {
        if (result.hasErrors()) {
            login.setUser(user);
            model.put("login", login);
            return VIEWS_LOGINS_UPDATE_FORM;
        } else {
            login.setEditor(HttpServletRequestUtil.getLogin(request));
            login.setEditorIP(HttpServletRequestUtil.getClientIp(request));

            user.addLogin(login);
            this.loginService.updateLogin(login);
            return "redirect:/users/{userId}";
        }
    }

    @GetMapping("/logins/{loginId}/delete")
    public String deleteLogin(@PathVariable("loginId") int loginId,@PathVariable("userId") int userId) {
        this.loginService.deleteLogin(loginId);

        return "redirect:/users/{userId}";

    }

    @GetMapping("/logins/{userName}/roles/binding")
    public String bindRole(@PathVariable("userName") String userName, ModelMap model, User user, Login login) {
        model.put("menu", "user list");
        Map<String, String> map = new HashMap<String, String>();
        map.put("userName",userName);

//        Collection<UserRole> results = this.roleService.findAll();
        Collection<UserRole> results = this.roleService.findRolesForLogin(map);
//        Collection<UserRole> selectedRoles = this.roleService.findLoginRoles(map);
        model.put("roles", results);
        return ROLE_BINDING_LIST;
    }

    @PostMapping("/logins/{userName}/roles/binding")
    public String bindRole(
            @ModelAttribute("user")User user,
            @RequestParam(name="role", required = false) String[] selectedRoles ,
            @PathVariable("userName") String userName,
            BindingResult bindingResult,  ModelMap model
            , HttpServletRequest request
            ) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("userName",userName);
        map.put("creator",HttpServletRequestUtil.getLogin(request));
        map.put("editor",HttpServletRequestUtil.getLogin(request));
        map.put("creatorIP",HttpServletRequestUtil.getClientIp(request));
        map.put("editorIP",HttpServletRequestUtil.getClientIp(request));

        if(selectedRoles != null) {
            roleService.bindRoles(map,selectedRoles);
        }
        else {
            roleService.unbindRoles(map);
        }
        return "redirect:/users/{userId}";
    }
}
