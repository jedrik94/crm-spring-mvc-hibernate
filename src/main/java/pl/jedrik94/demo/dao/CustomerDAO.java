package pl.jedrik94.demo.dao;

import pl.jedrik94.demo.model.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> getCustomers();
    void saveCustomer(Customer customer);
}
