package com.conectavida.conectavida.controller;

import com.conectavida.conectavida.model.Role;
import com.conectavida.conectavida.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam Role role) {

        switch (role) {
            case PATIENT -> authService.registerPatient(name, email, password);
            case PROVIDER -> authService.registerProvider(name, email, password);
            case INSTITUTION -> authService.registerInstitution(name, email, password);
            default -> throw new RuntimeException("Tipo de usuário inválido");
        }

        return "redirect:/login?registered";
    }
}