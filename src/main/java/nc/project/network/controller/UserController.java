package nc.project.network.controller;

import nc.project.network.entity.UserBase;
import nc.project.network.service.UserService;
import nc.project.network.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

//    @RequestMapping(value = "/registration", method = RequestMethod.GET)
//    public String registration(Model model) {
//        model.addAttribute("userForm", new UserBase());
//
//        return "registration";
//    }

//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public String registration(@ModelAttribute("userBaseForm") UserBase userBaseForm, BindingResult bindingResult, Model model) {
//        userValidator.validate(userBaseForm, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//
//        userService.save(userBaseForm);
//
//        return "redirect:/welcome";
//    }

//    @RequestMapping("/login")
//    public String getLogin(@RequestParam(value = "error", required = false) String error,
//                           @RequestParam(value = "logout", required = false) String logout,
//                           Model model) {
//        model.addAttribute("error", error != null);
//        model.addAttribute("logout", logout != null);
//        return "login";
//    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "hello";
    }

//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public String admin(Model model) {
//        return "admin";
//    }
}
