package com.conectavida.conectavida.controller;

import com.conectavida.conectavida.repository.ProviderRepository;
import com.conectavida.conectavida.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/institution")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private ProviderRepository providerRepository;

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        model.addAttribute("institution", institutionService.getByUserEmail(auth.getName()));
        return "institution/dashboard";
    }

    @GetMapping("/profile")
    public String profile(Authentication auth, Model model) {
        model.addAttribute("institution", institutionService.getByUserEmail(auth.getName()));
        return "institution/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(Authentication auth,
                                @RequestParam String cnpj,
                                @RequestParam String description,
                                @RequestParam String cep,
                                @RequestParam String address,
                                @RequestParam String phone,
                                @RequestParam(required = false) String website) {
        institutionService.updateProfile(auth.getName(), cnpj, description, cep, address, phone, website);
        return "redirect:/institution/profile?updated";
    }

    @GetMapping("/providers")
    public String providers(Authentication auth, Model model) {
        model.addAttribute("institution", institutionService.getByUserEmail(auth.getName()));
        model.addAttribute("allProviders", providerRepository.findAll());
        return "institution/providers";
    }

    @PostMapping("/providers/add")
    public String addProvider(Authentication auth, @RequestParam Long providerId) {
        institutionService.addProvider(auth.getName(), providerId);
        return "redirect:/institution/providers";
    }

    @PostMapping("/providers/remove")
    public String removeProvider(Authentication auth, @RequestParam Long providerId) {
        institutionService.removeProvider(auth.getName(), providerId);
        return "redirect:/institution/providers";
    }
}