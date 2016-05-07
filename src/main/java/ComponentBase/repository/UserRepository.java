package ComponentBase.repository;

import ComponentBase.role.Role;
import ComponentBase.address.Address;
import ComponentBase.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by waiti on 5/1/2016.
 */
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAll();
    List<User> findByNameContaining(String name);
    List<User> findBySurname(String surname);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findByPhoneNumber(String phoneNumber);
    List<User> findByAddresses(Address address);
    List<User> findByDob(Date dob);
    List<User> findByRoles(Role role);
}
