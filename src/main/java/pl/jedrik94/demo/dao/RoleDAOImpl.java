package pl.jedrik94.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.jedrik94.demo.model.Role;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public class RoleDAOImpl implements RoleDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role findByName(String roleName) {
        Session session = sessionFactory.getCurrentSession();
        Role roleQuery = null;

        Query<Role> query = session.createQuery("from Role r where r.name = :rName", Role.class);
        query.setParameter("rName", roleName);

        try {
            roleQuery = query.getSingleResult();
        } catch (NoResultException e) {
            System.err.println(e);
        }

        Optional<Role> optionalRole = Optional.ofNullable(roleQuery);

        return optionalRole.orElse(null);
    }
}
