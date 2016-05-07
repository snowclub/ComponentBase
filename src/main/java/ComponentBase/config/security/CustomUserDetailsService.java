package ComponentBase.config.security;

import ComponentBase.user.User;
import ComponentBase.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by panit on 5/4/2016.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByUsername(userName);
        if(user == null){
            throw new UsernameNotFoundException("User name" + userName + "not found");
        }
        return new SecurityUser(user);
    }
}
