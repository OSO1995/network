package nc.project.network.service;

import nc.project.network.entity.*;
import nc.project.network.repository.RequestRepository;
import nc.project.network.repository.RoleRepository;
import nc.project.network.repository.TariffRepository;
import nc.project.network.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TariffRepository tariffRepository;

    @Autowired
    private RequestRepository requestRepository;

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
    }

    @Override
    public void addRequest(String requestBody,Long userID) {
        Request request = new Request(userRepository.findById(userID).get(),requestBody);
        requestRepository.save(request);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public int calculateCost(Long userId, int necessarySpeed) throws ChangeSetPersister.NotFoundException {
        UserBase currentUserBase = userRepository.findById(userId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return isSimple(currentUserBase,necessarySpeed) ? 100 : 200;
    }

    private boolean isSimple(UserBase currentUser, int necessarySpeed) {
        Cable cable = currentUser.getPersonalInfo().getCable();
        return cable.getSpeed() > necessarySpeed;
    }

    @Override
    public User findByID(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
