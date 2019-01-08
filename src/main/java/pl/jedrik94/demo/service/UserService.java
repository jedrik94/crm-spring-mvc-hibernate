package pl.jedrik94.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.jedrik94.demo.model.User;
import pl.jedrik94.demo.user.CrmUser;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);

    void save(CrmUser user);
}
