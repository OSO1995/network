package nc.project.network.repository;

import nc.project.network.entity.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request,Long> {
}
