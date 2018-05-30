package nc.project.network.controller;

import nc.project.network.entity.Request;
import nc.project.network.entity.Role;
import nc.project.network.entity.User;
import nc.project.network.repository.RequestRepository;
import nc.project.network.service.UserService;
import nc.project.network.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestValidator requestValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(requestValidator);
    }

    @RequestMapping(value = "/request/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        model.addAttribute("link", "/user/request/add");
        model.addAttribute("request",new Request());
        return "/user/request/add";
    }

    @RequestMapping(value = "/request/add", method = RequestMethod.POST)
    public String add(@Valid Request requestForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/user/request/add";
        }

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        userService.addRequest(requestForm.getRequestBody(),currentUser.getId());

        return "redirect:/welcome";
    }
}
