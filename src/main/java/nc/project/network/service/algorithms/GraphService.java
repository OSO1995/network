package nc.project.network.service.algorithms;

import nc.project.network.entity.*;
import nc.project.network.service.algorithms.algorithmicEntities.Graph;
import nc.project.network.service.algorithms.algorithmicEntities.IEdge;
import nc.project.network.service.algorithms.algorithmicEntities.IVertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.IvParameterSpec;
import java.util.*;

@Service
public class GraphService {

    @Autowired
    private DijkstraService dijkstraService;

    public Graph getGraph(Area area) {
        Set<IVertex> vertexSet = new HashSet<>();
        vertexSet.addAll(getVertex(area));
        Set<IEdge> edges = new HashSet<>();
        edges.addAll(getEdges(area, vertexSet));
        return new Graph(vertexSet, edges);
    }

    private Collection<? extends IEdge> getEdges(Area area, Set<IVertex> vertexSet) {
        Set<IEdge> edges = new HashSet<>();
        area.getCables().forEach(x -> edges.add(edgesFactory(x)));
        vertexSet.forEach(x -> vertexHandler(x, edges));

        return edges;
    }

    private void vertexHandler(IVertex x, Set<IEdge> edges) {
        edges.forEach(edge -> {
            if (x.getDevice() instanceof Switch) {
                Switch aSwitch = (Switch) x.getDevice();
                aSwitch.getPorts().forEach(port -> {
                    setEdgeVertex(edge, x, port.getCable().toString());
                });
            }

            if (x.getDevice() instanceof User) {
                User user = (User) x.getDevice();
                setEdgeVertex(edge, x, user.getPersonalInfo().getCable().toString());
            }
        });
    }

    private void setEdgeVertex(IEdge edge, IVertex vertex, String currentCable) {
        String name = edge.getName();
        if (name.equals(currentCable)) {
            if (edge.getFirstVertex() == null) {
                edge.setFirstVertex(vertex);
            } else if (edge.getSecondVertex() == null) {
                edge.setSecondVertex(vertex);
            }
        }
    }


    private IEdge edgesFactory(Cable currentCable) {
        Cable tmp = currentCable;
        return new IEdge() {
            private Cable cable = tmp;

            private IVertex firstVertex;

            private IVertex secondVertex;

            @Override
            public String getName() {
                return this.cable.toString();
            }

            @Override
            public IVertex getFirstVertex() {
                return this.firstVertex;
            }

            @Override
            public void setFirstVertex(IVertex vertex) {
                this.firstVertex = vertex;
            }

            @Override
            public IVertex getSecondVertex() {
                return this.secondVertex;
            }

            @Override
            public void setSecondVertex(IVertex vertex) {
                this.secondVertex = vertex;
            }

            @Override
            public Cable getCable() {
                return cable;
            }
        };
    }

    private Collection<? extends IVertex> getVertex(Area area) {
        Set<IVertex> vertexSet = new HashSet<>();

        area.getSwitches().forEach(x -> vertexSet.add(devicesFactory(x)));
        area.getUsers().forEach(x -> vertexSet.add(devicesFactory(x)));

        return vertexSet;
    }

    private IVertex devicesFactory(Device x) {
        Device tmp = x;
        return new IVertex() {
            private Device device = tmp;

            private boolean visited;

            private IVertex previous;

            @Override
            public String getName() {
                return this.device.toString();
            }

            @Override
            public boolean isVisited() {
                return this.visited;
            }

            @Override
            public void setVisited(boolean visited) {
                this.visited = visited;
            }

            public Device getDevice() {
                return device;
            }

            @Override
            public IVertex getPrevious() {
                return previous;
            }

            @Override
            public void setPrevious(IVertex vertex) {
                this.previous = vertex;
            }

            @Override
            public String toString() {
                return "{" + device +
                        '}';
            }
        };
    }

    private List<IVertex> getAdjacentVertex(Graph G, IVertex currentVertex) {

        List<IVertex> result = new ArrayList<>();

        Set<IEdge> edges = G.getEdgeSet();
        edges.forEach(edge -> {

            IVertex first = edge.getFirstVertex();
            IVertex second = edge.getSecondVertex();

            if (first == currentVertex) {
                result.add(second);
            } else if (second == currentVertex) {
                result.add(first);
            }
        });

        return result;
    }

    public List<IVertex> getWay(Graph G, String start, String end, int parameter) {
        List<IVertex> result = new ArrayList<>();
        dijkstraService.start(G, start, parameter);
        IVertex[] endVertex = new IVertex[1];
        G.getVertexSet().forEach(vertex -> {
            if (end.equals(vertex.getName())) {
                endVertex[0] = vertex;
            }
        });

        fillAllPrevious(endVertex[0], result);

        return result;
    }

    private void fillAllPrevious(IVertex currentVertex, List<IVertex> result) {
        result.add(currentVertex);
        IVertex vertex = currentVertex.getPrevious();
        if (vertex.getPrevious() != null) {
            fillAllPrevious(vertex, result);
        }
    }
}
