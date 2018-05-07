package nc.projects.network.network.repository;

import nc.projects.network.network.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
