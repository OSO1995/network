package nc.project.network.service.algorithms;

import nc.project.network.entity.Area;
import nc.project.network.service.algorithms.algorithmicEntities.Graph;
import nc.project.network.service.algorithms.algorithmicEntities.IVertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

@Component
public class DepthFirstSearch {

    public void test(Graph graph, String startVertexName) {
        IVertex[] startVertex = new IVertex[1];
        graph.getVertexSet().forEach(vertex -> {
            if (vertex.getName().equals(startVertexName)) {
                startVertex[0] = vertex;
            }
        });

        Deque<IVertex> stack = new LinkedList<>();
        List<IVertex> way = new ArrayList<>();
        startDFS(graph, startVertex[0], stack, way);
    }

    private void startDFS(Graph graph, IVertex startVertex, Deque<IVertex> stack, List<IVertex> way) {
        startVertex.setVisited(true);
        stack.push(startVertex);

        way.add(startVertex);

        while (!stack.isEmpty()) {
            IVertex current = stack.peek();
            IVertex vertex = getSuccessor(current, graph);
            if (vertex == null) {
                stack.pop();
            } else {
                vertex.setVisited(true);
                way.add(vertex);
                stack.push(vertex);
            }
        }
    }

    private IVertex getSuccessor(IVertex current, Graph graph) {
        IVertex[] vertex = new IVertex[1];
        graph.getEdgeSet().forEach(edge -> {
            String name = current.getName();
            String firstVertexName = edge.getFirstVertex().getName();
            String secondVertexName = edge.getSecondVertex().getName();
            if (vertex[0] == null) {
                if (name.equals(firstVertexName) && !edge.getSecondVertex().isVisited()) {
                    vertex[0] = edge.getSecondVertex();
                } else if (name.equals(secondVertexName) && !edge.getFirstVertex().isVisited()) {
                    vertex[0] = edge.getFirstVertex();
                }
            }
        });
        return vertex[0];
    }

}
