package nc.project.network.service.algorithms;

import nc.project.network.service.algorithms.algorithmicEntities.DirectedGraph;
import nc.project.network.service.algorithms.algorithmicEntities.Graph;
import nc.project.network.service.algorithms.algorithmicEntities.IEdge;
import nc.project.network.service.algorithms.algorithmicEntities.IVertex;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class DijkstraService {

    public Map<IVertex,Double> start(Graph graph, String startVertexName) {
        Set<IVertex> vertexSet = graph.getVertexSet();
        IVertex startVertex = getStartVertex(vertexSet,startVertexName);

        DirectedGraph<IVertex> directedGraph = new DirectedGraph<>();
        fillGraph(directedGraph,graph);

        return Dijkstra.shortestPaths(directedGraph,startVertex);
    }

    private void fillGraph(DirectedGraph<IVertex> directedGraph, Graph graph) {
        Set<IVertex> vertexSet = graph.getVertexSet();
        Set<IEdge> edgeSet = graph.getEdgeSet();

        vertexSet.forEach(vertex -> directedGraph.addNode(vertex));
        edgeSet.forEach(iEdge -> {
            IVertex first = iEdge.getFirstVertex();
            IVertex second = iEdge.getSecondVertex();
            int cost = iEdge.getCable().getCost();
            directedGraph.addEdge(first,second,cost);
            directedGraph.addEdge(second,first,cost);
        });
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
