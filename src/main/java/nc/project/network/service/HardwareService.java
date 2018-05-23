package nc.project.network.service;

import nc.project.network.entity.*;
import nc.project.network.repository.AreaRepository;
import nc.project.network.repository.CableRepository;
import nc.project.network.repository.PortRepository;
import nc.project.network.repository.SwitchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class HardwareService {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private SwitchRepository switchRepository;

    @Autowired
    private PortRepository portRepository;

    @Autowired
    private CableRepository cableRepository;

    public Area getAreaById(Long id) throws ChangeSetPersister.NotFoundException {
        return areaRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public void createArea() {
        Set<Switch> switches = createSwitch(1);
        areaRepository.save(new Area(switches));
    }

    public Set<Switch> createSwitch(int count) {
        Set<Switch> switches = new HashSet<>();
        for (int i = 1; i < count; i++) {
            Set<Port> ports = createPorts();
            switches.add(new Switch(true,ports));
        }
        return switches;
    }

    private Set<Port> createPorts() {
        Set<Port> ports = new HashSet<>();
        for (int i = 0; i < 19; i++) {
            ports.add(new Port());
        }
        return ports;
    }


//    public Set<Cable> createCable(int count) {
//        Set<Cable> cables = new HashSet<>();
//        for (int j = 1; j < count; j++) {
//            cables.add(new Cable(300, false));
//        }
//        return cables;
//    }
}
