package com.conectavida.conectavida.controller;

import com.conectavida.conectavida.repository.UserRepository;
import com.conectavida.conectavida.service.DiseaseService;
import com.conectavida.conectavida.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DiseaseService diseaseService;
    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalUsers", userRepository.count());
        model.addAttribute("totalDiseases", diseaseService.findAll().size());
        model.addAttribute("totalSpecialties", specialtyService.findAll().size());
        return "admin/dashboard";
    }

    @GetMapping("/diseases")
    public String diseases(Model model) {
        model.addAttribute("diseases", diseaseService.findAll());
        return "admin/diseases";
    }

    @PostMapping("/diseases")
    public String createDisease(@RequestParam String name, @RequestParam(required = false) String description) {
        diseaseService.create(name, description);
        return "redirect:/admin/diseases";
    }

    @PostMapping("/diseases/delete/{id}")
    public String deleteDisease(@PathVariable Long id) {
        diseaseService.delete(id);
        return "redirect:/admin/diseases";
    }

    @GetMapping("/specialties")
    public String specialties(Model model) {
        model.addAttribute("specialties", specialtyService.findAll());
        return "admin/specialties";
    }

    @PostMapping("/specialties")
    public String createSpecialty(@RequestParam String name) {
        specialtyService.create(name);
        return "redirect:/admin/specialties";
    }

    @PostMapping("/specialties/delete/{id}")
    public String deleteSpecialty(@PathVariable Long id) {
        specialtyService.delete(id);
        return "redirect:/admin/specialties";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }
}