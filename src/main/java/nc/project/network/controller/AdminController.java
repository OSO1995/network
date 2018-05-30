package nc.project.network.controller;

import nc.project.network.entity.Role;
import nc.project.network.entity.Tariff;
import nc.project.network.entity.User;
import nc.project.network.service.UserService;
import nc.project.network.validator.RoleValidator;
import nc.project.network.validator.TariffValidator;
import nc.project.network.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/user")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RoleValidator roleValidator;

    @Autowired
    private TariffValidator tariffValidator;

    @InitBinder("tariff")
    protected void initTariffBinder(WebDataBinder binder) {
        binder.addValidators(tariffValidator);
    }

    @InitBinder("role")
    protected void initRoleBinder(WebDataBinder binder) {
        binder.addValidators(roleValidator);
    }

    @InitBinder("user")
    protected void initUserBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(ModelMap model) {
        model.addAttribute("link", "/user/add");
        model.addAttribute("user", new User());
        model.addAttribute("tariffs", userService.getTariffs());
        model.addAttribute("cables", userService.getCables());
        model.addAttribute("roles", userService.getRoles());
        return "/user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/user/add";
        }

        userForm.setEnabled(true);
        userForm.setCredentialsNonExpired(true);
        userForm.setAccountNonLocked(true);
        userForm.setAccountNonExpired(true);

        userService.save(userForm);

        return "/success";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/role/add", method = RequestMethod.GET)
    public String addRole(ModelMap model) {
        model.addAttribute("link", "/user/role/add");
        model.addAttribute("role", new Role());
        return "/user/role/add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/tariff/add", method = RequestMethod.GET)
    public String addTariff(ModelMap model) {

//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        model.addAttribute("link", "/user/tariff/add");
        model.addAttribute("tariff", new Tariff());
        return "/user/tariff/add";
    }

    @RequestMapping(value = "/role/add", method = RequestMethod.POST)
    public String addRole(@Valid Role roleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/user/role/add";
        }

        userService.addRole(roleForm);

        return "/success";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/tariff/add", method = RequestMethod.POST)
    public String addTariff(@Valid Tariff tariffForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user/tariff/add";
        }

        userService.addTariff(tariffForm);

        return "/success";
    }
}
