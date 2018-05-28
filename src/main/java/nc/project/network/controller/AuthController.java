package nc.project.network.controller;

import nc.project.network.service.HardwareService;
import nc.project.network.service.algorithms.DepthFirstSearch;
import nc.project.network.service.algorithms.algorithmicEntities.Graph;
import nc.project.network.service.algorithms.algorithmicEntities.IVertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private HardwareService hardwareService;

    @Autowired
    private DepthFirstSearch depthFirstSearch;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String hello(Model model) {
        return "hello";
    }

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = {"/start"}, method = RequestMethod.GET)
    public String test(Model model) {
        Graph G = hardwareService.getGraph();

        List<IVertex> way = depthFirstSearch.start(G,"Switch{id=10}");

        model.addAttribute("message", way.toString());
        
        return "start";
    }

    @RequestMapping(value = {"/ex"}, method = RequestMethod.GET)
    public void ex(Model model) throws Exception {

        throw new Exception();
    }
}
