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
package rzd.pktbcki.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import rzd.pktbcki.user.UserFormValidator;
import rzd.pktbcki.user.UserRole;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
class GroupController {

    private static final String VIEWS_GROUP_CREATE_OR_UPDATE_FORM = "groups/createOrUpdateGroupForm";

    @Autowired
    private GroupService groupService;

    @Autowired
    UserRoleFormValidator formValidator;
    	//Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(formValidator);
	}


    @GetMapping("/groups")
    public String showVetList(Map<String, Object> model) {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for Object-Xml mapping

        Collection<UserRole> results = this.groupService.findAll();
        model.put("selections", results);
        model.put("menu", "groups");
        return "groups/groupList";
    }

    @GetMapping("/groups/new")
    public String initCreationForm(Map<String, Object> model) {
        UserRole group = new UserRole();
        model.put("userRole", group);
//        model.put("group", group);
        model.put("menu", "add group");
        return VIEWS_GROUP_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/groups/new")
    public String processCreationForm(@Valid UserRole group, BindingResult result, Model model) {
        if (result.hasErrors()) {
//            model.addAttribute("group", group);
            return VIEWS_GROUP_CREATE_OR_UPDATE_FORM;
        } else {
            this.groupService.insertGroup(group);
            return "redirect:/groups";
        }
    }


/*

    @GetMapping("/groups/{groupId}/edit")
    public String initUpdateUserForm(@PathVariable("groupId") int groupId, Model model) {
        UserRole userRole = this.groupService.findById(groupId);
        model.addAttribute( userRole);
        model.addAttribute("menu", "groups");
        return VIEWS_GROUP_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/groups/{groupId}/edit")
    public String processUpdateUserForm(@Valid UserRole group, BindingResult result, @PathVariable("groupId") int groupId) {
        if (result.hasErrors()) {
            return VIEWS_GROUP_CREATE_OR_UPDATE_FORM;
        } else {
            group.setId(groupId);
            this.groupService.updateGroup(group);
            return "redirect:/groups";
        }
    }
*/


    @GetMapping("/groups/{roleName}/delete")
    public String deleteGroup(@PathVariable("roleName") String roleName) {
        this.groupService.deleteGroup(roleName);
        return "redirect:/groups";

    }

/*
    @GetMapping("/groups/{groupId}/delete")
    public String deleteGroup(@PathVariable("groupId") int id) {
        this.groupService.deleteGroup(id);
        return "redirect:/groups";

    }
*/
}
