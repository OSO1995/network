package nc.project.network.service;

import nc.project.network.entity.*;
import nc.project.network.repository.*;
import nc.project.network.service.algorithms.GraphService;
import nc.project.network.service.algorithms.algorithmicEntities.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class HardwareService {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private GraphService graphService;

    @PostConstruct
    private void init() {
        if (!areaRepository.findById(10L).isPresent()) {
            Cable[] cables = new Cable[6];
            Random random = new Random();
            for (int i = 0; i < cables.length; i++) {
                cables[i] = new Cable(100 + random.nextInt(300), 100, 5 + (random.nextInt(20)));
            }
            Port port1 = new Port(cables[0]);
            Port port2 = new Port(cables[1]);
            Port port3 = new Port(cables[1]);
            Port port4 = new Port(cables[2]);
            Port port5 = new Port(cables[5]);
            Port port6 = new Port(cables[0]);
            Port port7 = new Port(cables[2]);
            Port port8 = new Port(cables[4]);
            Port port9 = new Port(cables[3]);

            Set<Port> portsSwitch1 = new HashSet<>();
            portsSwitch1.add(port1);
            portsSwitch1.add(port2);
            Switch switch1 = new Switch(true, portsSwitch1);

            Set<Port> portsSwitch2 = new HashSet<>();
            portsSwitch2.add(port3);
            portsSwitch2.add(port4);
            portsSwitch2.add(port5);
            Switch switch2 = new Switch(true, portsSwitch2);

            Set<Port> portsSwitch3 = new HashSet<>();
            portsSwitch3.add(port6);
            portsSwitch3.add(port7);
            portsSwitch3.add(port8);
            portsSwitch3.add(port9);
            Switch switch3 = new Switch(true, portsSwitch3);

            Set<Switch> switches = new HashSet<>();
            switches.add(switch1);
            switches.add(switch2);
            switches.add(switch3);

            Area area = new Area();
            area.setSwitches(switches);
            area.setCables(new HashSet<>(Arrays.asList(cables)));

            Role role = new Role();
            role.setName("ROLE_TESTER");

            User user1 = new User();
            user1.setUsername("user1");
            user1.setPassword(new BCryptPasswordEncoder().encode("user1"));
            user1.setRoles(Collections.singleton(role));
            user1.setAccountNonExpired(true);
            user1.setAccountNonLocked(true);
            user1.setCredentialsNonExpired(true);
            user1.setEnabled(true);
            PersonalInfo personalInfo1 = new PersonalInfo();
            personalInfo1.setCable(cables[3]);
            user1.setPersonalInfo(personalInfo1);

            User user2 = new User();
            user2.setUsername("user2");
            user2.setPassword(new BCryptPasswordEncoder().encode("user2"));
            user2.setRoles(Collections.singleton(role));
            user2.setAccountNonExpired(true);
            user2.setAccountNonLocked(true);
            user2.setCredentialsNonExpired(true);
            user2.setEnabled(true);
            PersonalInfo personalInfo2 = new PersonalInfo();
            personalInfo2.setCable(cables[4]);
            user2.setPersonalInfo(personalInfo2);

            User user3 = new User();
            user3.setUsername("user3");
            user3.setPassword(new BCryptPasswordEncoder().encode("user3"));
            user3.setRoles(Collections.singleton(role));
            user3.setAccountNonExpired(true);
            user3.setAccountNonLocked(true);
            user3.setCredentialsNonExpired(true);
            user3.setEnabled(true);
            PersonalInfo personalInfo3 = new PersonalInfo();
            personalInfo3.setCable(cables[5]);
            user3.setPersonalInfo(personalInfo3);

            Set<User> users = new HashSet<>();
            users.add(user1);
            users.add(user2);
            users.add(user3);

            area.setUsers(users);

            areaRepository.save(area);
        }
    }


    public Graph getGraph() {
        List<Area> areas = new ArrayList<>();
        areaRepository.findAll().forEach(area -> areas.add(area));
        return graphService.getGraph(areas.get(0));
    }

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
            switches.add(new Switch(true, ports));
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

    public int getTimeDelay(Cable cable) {
        int length = cable.getLength();
        int speed = cable.getSpeed();
        if (length > 1000) {
            return speed / 2;
        } else if (length > 500) {
            return (int) (speed / 1.5);
        } else {
            return speed;
        }
    }


//    public Set<Cable> createCable(int count) {
//        Set<Cable> cables = new HashSet<>();
//        for (int j = 1; j < count; j++) {
//            cables.add(new Cable(300, false));
//        }
//        return cables;
//    }
}
