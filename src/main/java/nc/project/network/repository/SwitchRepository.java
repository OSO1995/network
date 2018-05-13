package nc.project.network.repository;

import nc.project.network.entity.Switch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwitchRepository extends CrudRepository<Switch,Long> {
}
