package nc.project.network.service.algorithms.algorithmicEntities;

import nc.project.network.entity.Cable;

public interface IEdge {
    String getName();

    IVertex getFirstVertex();

    void setFirstVertex(IVertex vertex);

    IVertex getSecondVertex();

    void setSecondVertex(IVertex vertex);

    Cable getCable();
}
