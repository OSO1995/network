package nc.project.network.controller;

import nc.project.network.controller.forms.Way;
import nc.project.network.entity.Request;
import nc.project.network.entity.Role;
import nc.project.network.entity.User;
import nc.project.network.repository.RequestRepository;
import nc.project.network.service.HardwareService;
import nc.project.network.service.UserService;
import nc.project.network.service.algorithms.GraphService;
import nc.project.network.service.algorithms.algorithmicEntities.Graph;
import nc.project.network.service.algorithms.algorithmicEntities.IVertex;
import nc.project.network.validator.RequestValidator;
import nc.project.network.validator.WayValidator;
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
import java.util.Collections;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private HardwareService hardwareService;

    @Autowired
    private GraphService graphService;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestValidator requestValidator;

    @Autowired
    private WayValidator wayValidator;

    @InitBinder("request")
    protected void initRequestBinder(WebDataBinder binder) {
        binder.addValidators(requestValidator);
    }

    @InitBinder("way")
    protected void initWayBinder(WebDataBinder binder) {
        binder.addValidators(wayValidator);
    }

    @RequestMapping(value = "/user/request/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        model.addAttribute("link", "/user/request/add");
        model.addAttribute("request",new Request());
        return "/user/request/add";
    }

    @RequestMapping(value = "/user/request/add", method = RequestMethod.POST)
    public String add(@Valid Request requestForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/user/request/add";
        }

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        userService.addRequest(requestForm.getRequestBody(),currentUser.getId());

        return "redirect:/welcome";
    }

    @RequestMapping(value = {"/chooseWay"}, method = RequestMethod.GET)
    public String getMyWay(Model model) {
        model.addAttribute("link", "/chooseWay");
        model.addAttribute("way", new Way());
        return "/chooseWay";
    }

    @RequestMapping(value = {"/chooseWay"}, method = RequestMethod.POST)
    public String setMyWay(@Valid Way wayForm,Model model) {

        Graph G = hardwareService.getGraph();
        List<IVertex> result = graphService.getWay(G,wayForm.getFirstVertex(),wayForm.getSecondVertex(),wayForm.getParameterWay());

        Collections.reverse(result);

        model.addAttribute("message", result.toString());

        return "start";
    }

}
