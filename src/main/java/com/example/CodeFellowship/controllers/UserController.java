package com.example.CodeFellowship.controllers;

import com.example.CodeFellowship.models.User;
import com.example.CodeFellowship.Services.ApplicationUserDetailsService;
import com.example.CodeFellowship.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Annotation specifies that this class will handle HTTP requests
@Controller
public class UserController {
    ApplicationUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Dependency injection of userDetailsService, userRepository, and passwordEncoder
    @Autowired
    public UserController(ApplicationUserDetailsService userDetailsService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Method handles the GET request to "/signup" URL
    // Which just returns the name of the signup view
    @GetMapping("/signup")
    public String getSignUp() {
        return "signup";  //name of thymeleaf template for signup
    }

    public ApplicationUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(ApplicationUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password, @RequestParam String firstName,
                         @RequestParam String lastName, @RequestParam String dateOfBirth, @RequestParam String bio) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);

        // Parse dateOfBirth from String to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(dateOfBirth, formatter);

        user.setDateOfBirth(dob);
        user.setBio(bio);

        userRepository.save(user);

        return "redirect:/login";
    }


    // Handle the GET request to "/login" URL
    // Returns the name of the login view
    @GetMapping("/login")
    public String getLogin() {
        return "login";  // This should be the name of your login Thymeleaf template (login.html)
    }
    @GetMapping("/")
    public String getHome() {
        return "home";
    }
}
