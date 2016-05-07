package ComponentBase;

import ComponentBase.repository.RoleRepository;
import ComponentBase.repository.UserRepository;
import ComponentBase.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ComponentBase.role.Role;

import java.util.HashSet;
import java.util.Set;
@EnableAutoConfiguration
@SpringBootApplication
public class ComponentBaseApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ComponentBaseApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		Role adminRole = new Role("admin");
		roleRepository.save(adminRole);
		Set<Role> roles = new HashSet<>();
		roles.add(adminRole);
		userRepository.save(new User("panit","panit","jaijaroen","panit_j@cmu.ac.th","1234",roles));
		userRepository.insert(new User("paniy","paniy","jaijaroen","panit_j@cmu.ac.th","1234",roles));

	}
}
