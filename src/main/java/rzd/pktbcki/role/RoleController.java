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
package rzd.pktbcki.role;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import rzd.pktbcki.system.HttpServletRequestUtil;
import rzd.pktbcki.user.User;
import rzd.pktbcki.user.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vladimir Nikishin
 */
@Controller
class RoleController {

    private static final String VIEWS_ROLE_CREATE_OR_UPDATE_FORM = "roles/createOrUpdateRoleForm";

    @Autowired
    private RoleService roleService;

    @Autowired
    UserRoleFormValidator formValidator;
    	//Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(formValidator);
	}

//    @RequiresRoles("adminapp")
    @GetMapping("/roles")
    public String showVetList(Map<String, Object> model) {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for Object-Xml mapping

        Collection<UserRole> results = this.roleService.findAll();
        model.put("selections", results);
        model.put("menu", "roles");
        return "roles/roleList";
    }

    @GetMapping("/roles/new")
    public String initCreationForm(Map<String, Object> model) {
        UserRole role = new UserRole();
        model.put("userRole", role);
//        model.put("role", role);
        model.put("menu", "add role");
        return VIEWS_ROLE_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/roles/new")
    public String processCreationForm(@Valid UserRole role, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
//            model.addAttribute("role", role);
            return VIEWS_ROLE_CREATE_OR_UPDATE_FORM;
        } else {
            role.setCreator(HttpServletRequestUtil.getLogin(request));
            role.setEditor(HttpServletRequestUtil.getLogin(request));
            role.setCreatorIP(HttpServletRequestUtil.getClientIp(request));
            role.setEditorIP(HttpServletRequestUtil.getClientIp(request));
            this.roleService.insertRole(role);
            return "redirect:/roles";
        }
    }


/*

    @GetMapping("/roles/{roleId}/edit")
    public String initUpdateUserForm(@PathVariable("roleId") int roleId, Model model) {
        UserRole userRole = this.roleService.findById(roleId);
        model.addAttribute( userRole);
        model.addAttribute("menu", "roles");
        return VIEWS_ROLE_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/roles/{roleId}/edit")
    public String processUpdateUserForm(@Valid UserRole role, BindingResult result, @PathVariable("roleId") int roleId) {
        if (result.hasErrors()) {
            return VIEWS_ROLE_CREATE_OR_UPDATE_FORM;
        } else {
            role.setId(roleId);
            this.roleService.updateRole(role);
            return "redirect:/roles";
        }
    }
*/


    @GetMapping("/roles/{roleName}/delete")
    public String deleteRole(@PathVariable("roleName") String roleName) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("roleName",roleName);

        this.roleService.deleteRole(map);
        return "redirect:/roles";

    }

    @GetMapping("/roles/{roleId}/delete/{userId}")
    public String deleteRole(@PathVariable("roleId") int roleId,@PathVariable("userId") int userId) {
        this.roleService.deleteRole(roleId);
        return "redirect:/users/{userId}";

    }
}
