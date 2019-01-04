package pl.jedrik94.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController {

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String showAccessDeniedPage() {
        return "access-denied";
    }
}
