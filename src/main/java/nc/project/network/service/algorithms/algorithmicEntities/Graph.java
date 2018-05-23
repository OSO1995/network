package nc.project.network.service.algorithms.algorithmicEntities;

import java.util.Set;

public class Graph {

    private Set<IVertex> vertexSet;

    private Set<IEdge> edgeSet;

    public Graph(Set<IVertex> vertexSet, Set<IEdge> edgeSet) {
        this.vertexSet = vertexSet;
        this.edgeSet = edgeSet;
    }

    public Set<IVertex> getVertexSet() {
        return vertexSet;
    }

    public void setVertexSet(Set<IVertex> vertexSet) {
        this.vertexSet = vertexSet;
    }

    public Set<IEdge> getEdgeSet() {
        return edgeSet;
    }

    public void setEdgeSet(Set<IEdge> edgeSet) {
        this.edgeSet = edgeSet;
    }
}
