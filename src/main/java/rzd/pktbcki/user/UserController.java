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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import rzd.pktbcki.mapper.UserMapper;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
class UserController {

    private static final String VIEWS_USER_CREATE_OR_UPDATE_FORM = "users/createOrUpdateUserForm";
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
    public String processCreationForm(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        } else {
            this.userService.insertUser(user);
            return "redirect:/users/" + user.getId();
        }
    }

    @GetMapping("/users/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("user", new User());
        model.put("menu", "find users");
        return "users/findUsers";
    }

    @GetMapping("/users")
    public String processFindForm(User user, BindingResult result, Map<String, Object> model) {


        // find users by last name

        Collection<User> results = this.userService.getUser(user);
        if (results.isEmpty()) {
            // no users found
            result.rejectValue("lastName", "notFound", "not found");
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


    @GetMapping("/users/{userId}/edit")
    public String initUpdateUserForm(@PathVariable("userId") int userId, Model model) {
        User user = this.userService.findById(userId);
        model.addAttribute(user);
        model.addAttribute("menu", "find users");
        return VIEWS_USER_CREATE_OR_UPDATE_FORM;
    }
    @PostMapping("/users/{userId}/edit")
    public String processUpdateUserForm(@Valid User user, BindingResult result, @PathVariable("userId") int userId) {
        if (result.hasErrors()) {
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        } else {
            user.setId(userId);
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
        ModelAndView mav = new ModelAndView("users/userDetails");
        mav.addObject(this.userService.findById(userId));
        mav.addObject("menu", "find users");
        return mav;
    }

}
