package nc.project.network.service.algorithms;

import nc.project.network.entity.Area;
import nc.project.network.service.algorithms.algorithmicEntities.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DepthFirstSearch {

    @Autowired
    GraphService graphService;

    public void test(Area area){
        Graph graph = graphService.getGraph(area);
    }

}
