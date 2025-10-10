package com.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/Signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "Signup";
    }

    @PostMapping("/Signup")
    public String signupSubmit(@ModelAttribute User user, Model model) {
        if(userService.isUsernameTaken(user.getUsername())) {
            model.addAttribute("error", "Username already taken!");
            return "Signup";
        }
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute User user, Model model) {
        User existingUser = userService.login(user.getUsername(), user.getPassword());
        if(existingUser != null) {
            model.addAttribute("username", existingUser.getUsername());
            return "welcome";
        } else {
            model.addAttribute("error", "Invalid credentials!");
            return "login";
        }
    }
}
