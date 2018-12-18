package pl.jedrik94.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.jedrik94.demo.model.Customer;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getCustomers() {
        Session session = sessionFactory.getCurrentSession();

        Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);

        return query.getResultList();
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Customer.class, id);
    }

    @Override
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("delete from Customer c where c.id = :customerId ");
        query.setParameter("customerId", id);

        query.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomer(String searchName) {
        Session session = sessionFactory.getCurrentSession();

        Query<Customer> query = session.createQuery("from Customer c where lower(c.firstName) like :searchName or lower(c.lastName) like :searchName", Customer.class);
        query.setParameter("searchName", "%" + searchName.toLowerCase() + "%");

        return query.getResultList();
    }
}
