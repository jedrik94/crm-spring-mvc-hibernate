package pl.jedrik94.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.jedrik94.demo.model.User;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        User queryUser = null;

        Query<User> query = session.createQuery("from User u where u.username = :uName", User.class);
        query.setParameter("uName", username);

        try {
            queryUser = query.getSingleResult();
        } catch (NoResultException e) {
            System.err.println(e);
        }

        Optional<User> optionalUser = Optional.ofNullable(queryUser);

        return optionalUser.orElse(null);
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(user);
    }
}
