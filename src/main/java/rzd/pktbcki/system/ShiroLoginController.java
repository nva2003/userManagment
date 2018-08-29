/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package rzd.pktbcki.system;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rzd.pktbcki.login.ChangePasswordValidator;
import rzd.pktbcki.login.LoginService;
import rzd.pktbcki.login.LoginValidator;
import rzd.pktbcki.user.Login;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Spring MVC controller responsible for authenticating the user.
 *
 * @since 0.1
 */
@Controller
public class ShiroLoginController {

    private static transient final Logger log = LoggerFactory.getLogger(ShiroLoginController.class);

    private static String LOGIN_VIEW = "signin";
    private static final String CHANGE_PASSWORD_FORM = "logins/changePassword";

    @Autowired
    private LoginService loginService;
    @Value("${application.system.id}")
    private Integer systemId;


    @Autowired
    private ChangePasswordValidator changePasswordValidator;

    @InitBinder("login")
    protected void initBinder(WebDataBinder binder) {
       binder.addValidators(changePasswordValidator);
    }


    @GetMapping("/signin")
    protected String view() {
        return LOGIN_VIEW;
    }

    @PostMapping("/signin")
    public String onSubmit(UserCredentials userCredentials, ModelMap model) {
//    public ModelAndView onSubmit(UserCredentials userCredentials, ModelMap model) {


        Subject subject = SecurityUtils.getSubject();

        if(!subject.isAuthenticated()) {
          UsernamePasswordToken token = new UsernamePasswordToken(
                  userCredentials.getUsername(), userCredentials.getPassword(), userCredentials.isRememberMe());
          try {
            subject.login(token);
          } catch (AuthenticationException ae) {
              log.debug("Error authenticating.", ae);
              model.addAttribute("errorInvalidLogin", "Введенный Вами логин или пароль неверен.");
//              model.addAttribute("errorInvalidLogin", "Введенный Вами логин или пароль неверен.The username or password was not correct.");
//              return new ModelAndView (LOGIN_VIEW);
              return LOGIN_VIEW;
          }

            if(loginService.isNeedToChangeThePassword(userCredentials.getUsername())){
//                model.addAttribute("username",userCredentials.getUsername());
//                return new ModelAndView("redirect:/needchangepassword", model);
                return "redirect:/needchangepassword";
//                return "forward:/needchangepassword";
            }

        }

        model.addAttribute("menu", "home");
        model.addAttribute("username", userCredentials.getUsername());

//        return new ModelAndView ("redirect:/");
        return "redirect:/";
    }

    @GetMapping("/needchangepassword")
    protected String needToChangeThePassword(ModelMap model) {

        Subject subject = SecurityUtils.getSubject();

        String username = subject.getPrincipal().toString();


        Login login = new Login();
        login.setUserName(username);

        model.put("login", login);


        return CHANGE_PASSWORD_FORM;
    }


    @PostMapping("/needchangepassword")
    public String setNewPassword(@Valid Login login,  BindingResult bindingResult, Model model, HttpServletRequest request) {


        if (bindingResult.hasErrors()) {
                   return CHANGE_PASSWORD_FORM;
        }

        login.setEditor(HttpServletRequestUtil.getLogin(request));
        login.setEditorIP(HttpServletRequestUtil.getClientIp(request));

        this.loginService.changePassword(login);


        model.addAttribute("menu", "home");
        model.addAttribute("username", login.getUserName());
        return "redirect:/";
    }

}