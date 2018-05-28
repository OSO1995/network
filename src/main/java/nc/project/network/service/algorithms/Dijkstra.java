package nc.project.network.service.algorithms;

import nc.project.network.service.algorithms.algorithmicEntities.Graph;
import nc.project.network.service.algorithms.algorithmicEntities.IVertex;

import javax.crypto.spec.IvParameterSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Dijkstra {

    public List<IVertex> start(Graph graph, String startVertexName) {
        IVertex[] startVertex = new IVertex[1];
        graph.getVertexSet().forEach(vertex -> {
            if (vertex.getName().equals(startVertexName)) {
                startVertex[0] = vertex;
            }
        });

        List<IVertex> way = new ArrayList<>();
        runDijkstra(graph,startVertex[0]);
        return way;
    }

    private void runDijkstra(Graph graph, IVertex startVertex) {
        startVertex.setTimeDelay(0);
        Set<IVertex> vertexSet = graph.getVertexSet();

    }
}
