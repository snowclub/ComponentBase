package ComponentBase.user;

import ComponentBase.address.Address;
import ComponentBase.role.Role;

import java.util.Date;
import java.util.List;

/**
 * Created by waiti on 5/1/2016.
 */
public interface UserDao {
    List<User> getUsers();
    List<User> findByName(String name);
    List<User> findBySurname(String surname);
    User findByUsername(String Username);
    User findByEmail(String email);
    List<User> findByPhoneNumber(String phoneNumber);
    List<User> findByAddresses(Address address);
    List<User> findByDob(Date dob);
    List<User> findByRoles(Role role);
    User create(User user);
    User edit(User user);
    User delete(User user);
    User getUser(String id);
}
