package pl.jedrik94.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String showHomePage() {
        return "home";
    }

    @RequestMapping(value = "/managerPage", method = RequestMethod.GET)
    public String showManagerPage() {
        return "manager-welcome";
    }

    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public String showAdminPage() {
        return "admin-welcome";
    }
}
