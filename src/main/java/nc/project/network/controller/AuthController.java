package nc.project.network.controller;

import nc.project.network.service.HardwareService;
import nc.project.network.service.algorithms.DepthFirstSearch;
import nc.project.network.service.algorithms.DijkstraService;
import nc.project.network.service.algorithms.GraphService;
import nc.project.network.service.algorithms.algorithmicEntities.Graph;
import nc.project.network.service.algorithms.algorithmicEntities.IVertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private HardwareService hardwareService;

    @Autowired
    private DepthFirstSearch depthFirstSearch;

    @Autowired
    private DijkstraService dijkstraService;

    @Autowired
    private GraphService graphService;

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

        List<IVertex> result = graphService.getWay(G,"User{id=13}","User{id=14}");
//        Map<IVertex,Double> all = dijkstraService.start(G,"Switch{id=11}");

        Collections.reverse(result);

        model.addAttribute("message", result.toString());
        
        return "start";
    }

    @RequestMapping(value = {"/ex"}, method = RequestMethod.GET)
    public void ex(Model model) throws Exception {

        throw new Exception();
    }
}
