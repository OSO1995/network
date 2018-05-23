package nc.project.network.service.algorithms.algorithmicEntities;

public interface IEdge {
    String getName();

    IVertex getFirstVertex();

    void setFirstVertex(IVertex vertex);

    IVertex getSecondVertex();

    void setSecondVertex(IVertex vertex);
}
