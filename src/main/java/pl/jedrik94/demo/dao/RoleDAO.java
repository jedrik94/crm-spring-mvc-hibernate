package pl.jedrik94.demo.dao;

import pl.jedrik94.demo.model.Role;

public interface RoleDAO {
    Role findByName(String roleName);
}
