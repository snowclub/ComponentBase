package ComponentBase.repository;

import ComponentBase.role.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by panit on 5/4/2016.
 */
public interface RoleRepository extends MongoRepository<Role, String> {
}
