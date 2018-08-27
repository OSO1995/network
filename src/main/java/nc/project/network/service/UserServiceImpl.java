package nc.project.network.service;

import nc.project.network.entity.*;
import nc.project.network.repository.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TariffRepository tariffRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private CableRepository cableRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void addTariff(Tariff tariff) {
        tariffRepository.save(tariff);
        logger.log(Level.INFO, "__________Add new tariff__________");
    }

    @Override
    public void addRequest(String requestBody, Long userID) {
        Request request = new Request(userRepository.findById(userID).get(), requestBody);
        requestRepository.save(request);
        logger.log(Level.INFO, "__________Add new request__________");
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public int calculateCost(Long userId, int necessarySpeed) throws ChangeSetPersister.NotFoundException {
        User currentUser = userRepository.findById(userId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return isSimple(currentUser, necessarySpeed) ? 100 : 200;
    }

    private boolean isSimple(User currentUser, int necessarySpeed) {
        Cable cable = currentUser.getPersonalInfo().getCable();
        return cable.getSpeed() > necessarySpeed;
    }

    @Override
    public User findByID(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void addRole(Role roleForm) {
        roleRepository.save(roleForm);
        logger.log(Level.INFO, "__________Add new role__________");
    }

    @Override
    public Object getTariffs() {
        return tariffRepository.findAll();
    }

    @Override
    public Object getCables() {
        return cableRepository.findAll();
    }

    @Override
    public Object getRoles() {
        return roleRepository.findAll();
    }

}
