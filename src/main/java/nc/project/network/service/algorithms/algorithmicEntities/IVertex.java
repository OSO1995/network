package nc.project.network.service.algorithms.algorithmicEntities;

import nc.project.network.entity.Device;

public interface IVertex {
    String getName();

    boolean isVisited();

    void setVisited(boolean visited);

    boolean isBlack();

    void setBlack(boolean black);

    Device getDevice();
}
