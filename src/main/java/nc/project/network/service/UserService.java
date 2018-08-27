package nc.project.network.service;

import nc.project.network.entity.Role;
import nc.project.network.entity.Tariff;
import nc.project.network.entity.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    void delete(Long id);

    void save(User user);

    void addTariff(Tariff tariff);

    int calculateCost(Long userId, int speed) throws ChangeSetPersister.NotFoundException;

    void addRequest(String request, Long userID);

    User findByUsername(String username);

    User findByID(Long id);

    void addRole(Role roleForm);

    Object getTariffs();

    Object getCables();

    Object getRoles();
}
