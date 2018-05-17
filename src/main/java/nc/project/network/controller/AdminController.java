package nc.project.network.controller;

import nc.project.network.entity.User;
import nc.project.network.repository.CableRepository;
import nc.project.network.repository.RoleRepository;
import nc.project.network.repository.TariffRepository;
import nc.project.network.repository.UserRepository;
import nc.project.network.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class AdminController {

    @Autowired
    TariffRepository tariffRepository;

    @Autowired
    CableRepository cableRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(ModelMap model) {
        model.addAttribute("link", "/user/add");
        model.addAttribute("user",new User());
        model.addAttribute("tariffs",tariffRepository.findAll());
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


}
