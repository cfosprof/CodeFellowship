package com.example.CodeFellowship.controllers;

import com.example.CodeFellowship.models.Post;
import com.example.CodeFellowship.models.ApplicationUser;
import com.example.CodeFellowship.Services.ApplicationUserDetailsService;
import com.example.CodeFellowship.repositories.PostRepository;
import com.example.CodeFellowship.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class UserController {
    private final ApplicationUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PostRepository postRepository;

    @Autowired
    public UserController(ApplicationUserDetailsService userDetailsService, UserRepository userRepository, PasswordEncoder passwordEncoder, PostRepository postRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/signup")
    public String getSignUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password, @RequestParam String firstName,
                         @RequestParam String lastName, @RequestParam String dateOfBirth, @RequestParam String bio) {
        ApplicationUser user = new ApplicationUser();
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

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/post/new")
    public String getNewPost() {
        return "newpost";
    }

    @PostMapping("/post/new")
    public String createPost(Authentication auth, @RequestParam String title, @RequestParam String body) {
        if (auth.isAuthenticated()) {
            if (!title.isEmpty() && !body.isEmpty()) {
                UserDetails user = (UserDetails) auth.getPrincipal();
                ApplicationUser applicationUser = userRepository.findByUsername(user.getUsername());
                Post post = new Post(title, body, applicationUser);
                postRepository.save(post);
                return "redirect:/myprofile";
            } else {
                return "error";
            }
        } else {
            return "login";
        }
    }

    @GetMapping("/myprofile")
    public String getMyProfile(Authentication auth, Model model) {
        ApplicationUser user = (ApplicationUser) auth.getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("posts", postRepository.findByAuthor(user));
        return "myprofile";
    }

    @GetMapping("/post/id/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/post/{id}/edit")
    public String editPost(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return "editpost";
    }

    @PostMapping("/post/{id}/edit")
    public String updatePost(@PathVariable Long id, @RequestParam String body, Authentication auth) {
        ApplicationUser user = (ApplicationUser) auth.getPrincipal();
        Post post = postRepository.findById(id).orElseThrow();
        if (post.getAuthor().equals(user)) {
            post.setBody(body);
            postRepository.save(post);
        }
        return "redirect:/myprofile";
    }



    @PostMapping("/post/{id}/delete")
    public String deletePost(@PathVariable Long id, Authentication auth) {
        ApplicationUser user = (ApplicationUser) auth.getPrincipal();
        Post post = postRepository.findById(id).orElseThrow();
        if (post.getAuthor().equals(user)) {
            postRepository.deleteById(id);
        }
        return "redirect:/myprofile";
    }



}
