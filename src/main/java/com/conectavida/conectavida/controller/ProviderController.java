package com.conectavida.conectavida.controller;

import com.conectavida.conectavida.repository.SpecialtyRepository;
import com.conectavida.conectavida.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;
    @Autowired
    private SpecialtyRepository specialtyRepository;

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        model.addAttribute("provider", providerService.getByUserEmail(auth.getName()));
        return "provider/dashboard";
    }

    @GetMapping("/profile")
    public String profile(Authentication auth, Model model) {
        model.addAttribute("provider", providerService.getByUserEmail(auth.getName()));
        return "provider/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(Authentication auth,
                                @RequestParam String registrationNumber,
                                @RequestParam String profession,
                                @RequestParam String bio,
                                @RequestParam String phone,
                                @RequestParam String cep,
                                @RequestParam String address) {
        providerService.updateProfile(auth.getName(), registrationNumber, profession, bio, phone, cep, address);
        return "redirect:/provider/profile?updated";
    }

    @GetMapping("/specialties")
    public String specialties(Authentication auth, Model model) {
        model.addAttribute("provider", providerService.getByUserEmail(auth.getName()));
        model.addAttribute("allSpecialties", specialtyRepository.findAll());
        return "provider/specialties";
    }

    @PostMapping("/specialties/add")
    public String addSpecialty(Authentication auth, @RequestParam Long specialtyId) {
        providerService.addSpecialty(auth.getName(), specialtyId);
        return "redirect:/provider/specialties";
    }

    @PostMapping("/specialties/remove")
    public String removeSpecialty(Authentication auth, @RequestParam Long specialtyId) {
        providerService.removeSpecialty(auth.getName(), specialtyId);
        return "redirect:/provider/specialties";
    }
}