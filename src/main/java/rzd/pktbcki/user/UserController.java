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
package rzd.pktbcki.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rzd.pktbcki.mapper.UserMapper;
import rzd.pktbcki.system.HttpServletRequestUtil;
import rzd.pktbcki.util.StringToTimestampPropertyEditorSupport;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author V.N.
 */
@Controller
class UserController {

    @Value("${application.system.name}")
    private String systemName;


    private static final String VIEWS_USER_CREATE_OR_UPDATE_FORM = "users/createOrUpdateUserForm";
    private static final String USER_DETAILS_FORM = "users/userDetails";
    /*
        private final UserRepository users;


        @Autowired
        public UserController(UserRepository clinicService) {
            this.users = clinicService;
        }
    */
    @Autowired
    private UserService userService;

    @Autowired
   	UserFormValidator userFormValidator;
    	//Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
        binder.registerCustomEditor(Timestamp.class, "timeBefore",  new StringToTimestampPropertyEditorSupport());
	}

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/users/new")
    public String initCreationForm(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        model.put("menu", "add user");
        return VIEWS_USER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/users/new")
    public String processCreationForm(@Valid User user, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        } else {
            user.setCreator(HttpServletRequestUtil.getLogin(request));
            user.setEditor(HttpServletRequestUtil.getLogin(request));
            user.setCreatorIP(HttpServletRequestUtil.getClientIp(request));
            user.setEditorIP(HttpServletRequestUtil.getClientIp(request));
            user = this.userService.insertUser(user);
            return "redirect:/users/" + user.getId();
        }
    }

    @GetMapping("/users/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("user", new User());
        model.put("menu", "find users");
        return "users/findUsers";
    }

    @PostMapping("/users")
    public String processFindForm(User user, BindingResult result, Map<String, Object> model) {


        // find users by last name

        Collection<User> results = this.userService.getUser(user);
        if (results.isEmpty()) {
            // no users found
            model.put("msg", "Пользователь с заданами параметрами не найден");
            model.put("menu", "find users");
            return "users/findUsers";
        } else if (results.size() == 1) {
            // 1 user found
            user = results.iterator().next();
            return "redirect:/users/" + user.getId();
        } else
        {
            // multiple users found
            model.put("selections", results);
            model.put("menu", "user list");
            return "users/usersList";
        }
    }

    @GetMapping("/users")
    public String initUserListForm(User user, BindingResult result, Map<String, Object> model) {



        Collection<User> results = this.userService.getUser(user);
        if (results.isEmpty()) {
            // no users found
            model.put("menu", "add user");
            return "redirect:/users/new";
        } else if (results.size() == 1) {
            // 1 user found
            user = results.iterator().next();
            return "redirect:/users/" + user.getId();
        } else
        {
            // multiple users found
            model.put("selections", results);
            model.put("menu", "user list");
            return "users/usersList";
        }
    }


    @GetMapping("/users/{userId}/edit")
    public String initUpdateUserForm(@PathVariable("userId") int userId, Model model) {
        User user = this.userService.findById(userId);
        model.addAttribute(user);
        model.addAttribute("menu", "user list");
        return VIEWS_USER_CREATE_OR_UPDATE_FORM;
    }
    @PostMapping("/users/{userId}/edit")
    public String processUpdateUserForm(@Valid User user, BindingResult result, @PathVariable("userId") int userId, HttpServletRequest request) {
        if (result.hasErrors()) {
            user.setId(userId);
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        } else {
            user.setId(userId);
            user.setEditor(HttpServletRequestUtil.getLogin(request));
            user.setEditorIP(HttpServletRequestUtil.getClientIp(request));

            this.userService.updateUser(user);
            return "redirect:/users/{userId}";
        }
    }


    @GetMapping("/users/{userId}/delete")
    public String deleteUser(@PathVariable("userId") int userId) {
        this.userService.deleteUser(userId);
        return "redirect:/users";

    }


    /**
     * Custom handler for displaying an user.
     *
     * @param userId the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @GetMapping("/users/{userId}")
    public ModelAndView showUser(@PathVariable("userId") int userId) {
        ModelAndView mav = new ModelAndView(USER_DETAILS_FORM);
//        mav.addObject(this.userService.findById(userId));
        mav.addObject(this.userService.findByIdWithLogin(userId));
        mav.addObject("menu", "user list");
        mav.addObject("systemName", systemName);
        return mav;
    }

}
