package ComponentBase.user;

import ComponentBase.address.Address;
import ComponentBase.repository.UserRepository;
import ComponentBase.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by waiti on 5/1/2016.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByNameContaining(name);
    }

    @Override
    public List<User> findBySurname(String surname) {
        return userRepository.findBySurname(surname);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<User> findByAddresses(Address address) {
        return userRepository.findByAddresses(address);
    }

    @Override
    public List<User> findByDob(Date dob) {
        return userRepository.findByDob(dob);
    }

    @Override
    public List<User> findByRoles(Role role) {
        return userRepository.findByRoles(role);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User edit(User user) {
        return userRepository.save(user);
    }

    @Override
    public User delete(User user) {
        userRepository.delete(user);
        user.setId(null);
        return user;
    }

    @Override
    public User getUser(String id) {
        return userRepository.findOne(id);
    }
}
