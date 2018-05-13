package nc.project.network.repository;

import nc.project.network.entity.Port;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortRepository extends CrudRepository<Port,Long> {
}
