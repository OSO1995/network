package nc.project.network.controller;

import nc.project.network.entity.Role;
import nc.project.network.entity.Tariff;
import nc.project.network.entity.User;
import nc.project.network.repository.CableRepository;
import nc.project.network.repository.RoleRepository;
import nc.project.network.repository.TariffRepository;
import nc.project.network.repository.UserRepository;
import nc.project.network.service.UserService;
import nc.project.network.validator.RoleValidator;
import nc.project.network.validator.TariffValidator;
import nc.project.network.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class AdminController {

    @Autowired
    TariffRepository tariffRepository;

    @Autowired
    UserService userService;

    @Autowired
    CableRepository cableRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleValidator roleValidator;

    @Autowired
    private TariffValidator tariffValidator;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(ModelMap model) {
        model.addAttribute("link", "/user/add");
        model.addAttribute("user", new User());
        model.addAttribute("tariffs", tariffRepository.findAll());
        model.addAttribute("cables", cableRepository.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        return "/user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/user/add";
        }

        userForm.setPassword(new BCryptPasswordEncoder().encode(userForm.getPassword()));
        userForm.setEnabled(true);
        userForm.setCredentialsNonExpired(true);
        userForm.setAccountNonLocked(true);
        userForm.setAccountNonExpired(true);

        userRepository.save(userForm);

        return "redirect:/welcome";
    }


    @RequestMapping(value = "/role/add", method = RequestMethod.GET)
    public String addRole(ModelMap model) {
        model.addAttribute("link", "/user/role/add");
        model.addAttribute("role", new Role());
        return "/user/role/add";
    }

    @RequestMapping(value = "/role/add", method = RequestMethod.POST)
    public String addRole(@ModelAttribute("userForm") Role roleForm, BindingResult bindingResult, Model model) {
        roleValidator.validate(roleForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/user/role/add";
        }

        roleRepository.save(roleForm);

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/tariff/add", method = RequestMethod.GET)
    public String addTariff(ModelMap model) {
        model.addAttribute("link", "/user/tariff/add");
        model.addAttribute("tariff", new Tariff());
        return "/user/tariff/add";
    }

    @RequestMapping(value = "/tariff/add", method = RequestMethod.POST)
    public String addTariff(@Valid Tariff tariffForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user/tariff/add";
        }

        userService.addTariff(tariffForm);

        return "redirect:/welcome";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(tariffValidator);
    }


}
