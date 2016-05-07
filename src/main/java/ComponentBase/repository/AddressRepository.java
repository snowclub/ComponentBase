package ComponentBase.repository;

import ComponentBase.address.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by panit on 5/7/2016.
 */
public interface AddressRepository extends MongoRepository<Address, String> {
}
