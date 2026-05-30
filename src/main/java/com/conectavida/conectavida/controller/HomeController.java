package com.conectavida.conectavida.controller;

import com.conectavida.conectavida.model.User;
import com.conectavida.conectavida.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        return switch (user.getRole()) {
            case PATIENT -> "redirect:/patient/dashboard";
            case PROVIDER -> "redirect:/provider/dashboard";
            case INSTITUTION -> "redirect:/institution/dashboard";
            case ADMIN -> "redirect:/admin/dashboard";
        };
    }
}