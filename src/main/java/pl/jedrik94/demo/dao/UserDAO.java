package pl.jedrik94.demo.dao;

import pl.jedrik94.demo.model.User;

public interface UserDAO {
    User findByUsername(String userName);

    void save(User user);
}
