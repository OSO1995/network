package nc.project.network.service.algorithms;

import nc.project.network.service.HardwareService;
import nc.project.network.service.algorithms.algorithmicEntities.DirectedGraph;
import nc.project.network.service.algorithms.algorithmicEntities.Graph;
import nc.project.network.service.algorithms.algorithmicEntities.IEdge;
import nc.project.network.service.algorithms.algorithmicEntities.IVertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class DijkstraService {

    @Autowired
    private HardwareService hardwareService;

    public Map<IVertex, Double> start(Graph graph, String startVertexName, int parameter) {
        Set<IVertex> vertexSet = graph.getVertexSet();
        IVertex startVertex = getStartVertex(vertexSet, startVertexName);

        DirectedGraph<IVertex> directedGraph = new DirectedGraph<>();
        fillGraph(directedGraph, graph, parameter);

        return Dijkstra.shortestPaths(directedGraph, startVertex);
    }

    private void fillGraph(DirectedGraph<IVertex> directedGraph, Graph graph, int parameter) {
        Set<IVertex> vertexSet = graph.getVertexSet();
        Set<IEdge> edgeSet = graph.getEdgeSet();

        vertexSet.forEach(vertex -> directedGraph.addNode(vertex));
        edgeSet.forEach(iEdge ->
                fillEdge(directedGraph, iEdge, parameter));
    }

    private void fillEdge(DirectedGraph<IVertex> directedGraph, IEdge iEdge, int parameter) {
        IVertex first = iEdge.getFirstVertex();
        IVertex second = iEdge.getSecondVertex();
        if (parameter == 0) {
            int cost = iEdge.getCable().getCost();
            addEdge(directedGraph, first, second, cost);
        } else {
            int timeDelay = hardwareService.getTimeDelay(iEdge.getCable());
            addEdge(directedGraph, first, second, timeDelay);
        }
    }

    private void addEdge(DirectedGraph<IVertex> directedGraph, IVertex first, IVertex second, int priority) {
        directedGraph.addEdge(first, second, priority);
        directedGraph.addEdge(second, first, priority);
    }

    private IVertex getStartVertex(Set<IVertex> vertexSet, String startVertexName) {
        IVertex[] result = new IVertex[1];
        vertexSet.forEach(vertex -> {
            String name = vertex.getName();
            if (name.equals(startVertexName)) {
                result[0] = vertex;
            }
        });

        return result[0];
    }


}
