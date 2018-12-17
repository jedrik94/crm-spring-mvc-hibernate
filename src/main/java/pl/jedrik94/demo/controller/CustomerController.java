package pl.jedrik94.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jedrik94.demo.dao.CustomerDAO;
import pl.jedrik94.demo.model.Customer;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    final CustomerDAO customerDAO;

    @Autowired
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @RequestMapping("/list")
    public String listCustomer(Model model) {

        List<Customer> customerList = customerDAO.getCustomers();

        model.addAttribute("customers", customerList);

        return "list-customer";
    }
}
