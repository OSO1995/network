package nc.project.network.repository;

import nc.project.network.entity.Cable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CableRepository extends CrudRepository<Cable, Long> {
}
