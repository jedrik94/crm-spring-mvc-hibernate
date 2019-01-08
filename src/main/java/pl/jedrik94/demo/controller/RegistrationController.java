package pl.jedrik94.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.jedrik94.demo.model.User;
import pl.jedrik94.demo.service.UserService;
import pl.jedrik94.demo.user.CrmUser;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/register")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping(value = "/showRegistrationForm", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("crmUser", new CrmUser());
        return "registration-form";
    }

    @RequestMapping(value = "/processRegistrationForm", method = RequestMethod.POST)
    public String processRegistrationForm(@Valid @ModelAttribute("crmUser") CrmUser crmUser, BindingResult bindingResult, Model model) {

        String username = crmUser.getUsername();

        if (bindingResult.hasErrors()) {
            return "registration-form";
        }

        Optional<User> existingUser = Optional.ofNullable(userService.findByUsername(username));

        if (existingUser.isPresent()) {
            model.addAttribute("crmUser", new CrmUser());
            model.addAttribute("registrationError", "Username is already used!");

            return "registration-form";
        }

        userService.save(crmUser);

        model.addAttribute("registrationSuccess", "Registration Successful!");

        return "registration-success";
    }
}
