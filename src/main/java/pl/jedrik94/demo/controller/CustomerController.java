package pl.jedrik94.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String listCustomers(Model model) {
        List<Customer> customerList = customerService.getCustomers();

        model.addAttribute("customers", customerList);

        return "list-customer";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String showFormForAddCustomer(Model model) {

        Customer customer = new Customer();

        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @RequestMapping(value = "/formUpdate", method = RequestMethod.GET)
    public String updateCustomer(@RequestParam("customerId") int id, Model model) {

        Customer customer = customerService.getCustomer(id);

        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteCustomer(@RequestParam("customerId") int id) {
        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchCustomer(@RequestParam("searchName") String searchName, Model model) {

        List<Customer> customerList = customerService.searchCustomer(searchName);

        model.addAttribute("customers", customerList);

        return "list-customer";
    }
}
