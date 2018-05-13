package nc.project.network.repository;

import nc.project.network.entity.Area;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends CrudRepository<Area,Long> {
}
