package nc.project.network.service.algorithms.algorithmicEntities;

import nc.project.network.entity.Device;

public interface IVertex {
    String getName();

    boolean isVisited();

    void setVisited(boolean visited);

    Device getDevice();
}
