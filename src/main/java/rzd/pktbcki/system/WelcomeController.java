package rzd.pktbcki.system;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import rzd.pktbcki.user.Login;
import rzd.pktbcki.user.User;
import rzd.pktbcki.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.*;
import java.util.Map;

@Controller
//@SessionAttributes("RemoteUser")
class WelcomeController {

    private static final String VIEWS_HOME = "welcome";
    /*
        @ModelAttribute("RemoteUser")
        public String getRemoteUser (HttpServletRequest request) {
            System.out.println(request.getSession().getAttribute("RemoteUserIP"));
            return request.getUserPrincipal().getName();
    //        return "RemoteUser111"; //get From DB Or Session
        }
    */
    @Autowired
    private SystemService systemService;
    @Value("${application.system.id}")
    private Integer systemId;


    @InitBinder("applicationSystem")
    public void initSystemBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new ApplicationSystemValidator());
    }


    @GetMapping("/")
    public String welcome(Map<String, Object> model ) {
        ApplicationSystem applicationSystem = new ApplicationSystem();
/*
        system.setAuditId(auditId);
        system.setSystemId(systemId);
        system.setSystemName(systemName);
*/
        applicationSystem = systemService.findById(systemId);
        model.put("menu", "home");
        model.put("applicationSystem", applicationSystem);

        return "welcome";
    }

    @PostMapping("/")
    public String processCreationForm(@Valid ApplicationSystem applicationSystem, BindingResult result, ModelMap model) {
/*
        if (StringUtils.hasLength(login.getUserName()) && login.isNew() && user.getLogin(login.getUserName(), true) != null){
            result.rejectValue("userName", "duplicate", "already exists");
        }
*/
        if (result.hasErrors()) {
            model.put("menu", "home");
//            model.put("login", login);
            return VIEWS_HOME;
        } else {

            this.systemService.updateSystem(applicationSystem);
            return "redirect:/";
        }
    }

}
