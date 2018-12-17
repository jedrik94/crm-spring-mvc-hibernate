package pl.jedrik94.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.jedrik94.demo.model.Customer;
import pl.jedrik94.demo.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listCustomer(Model model) {
        List<Customer> customerList = customerService.getCustomers();

        model.addAttribute("customers", customerList);

        return "list-customer";
    }
}
