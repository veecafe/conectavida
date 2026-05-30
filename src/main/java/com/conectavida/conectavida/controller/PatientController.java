package com.conectavida.conectavida.controller;

import com.conectavida.conectavida.repository.DiseaseRepository;
import com.conectavida.conectavida.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private DiseaseRepository diseaseRepository;

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        model.addAttribute("patient", patientService.getByUserEmail(auth.getName()));
        return "patient/dashboard";
    }

    @GetMapping("/profile")
    public String profile(Authentication auth, Model model) {
        model.addAttribute("patient", patientService.getByUserEmail(auth.getName()));
        return "patient/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(Authentication auth,
                                @RequestParam String cpf,
                                @RequestParam String phone,
                                @RequestParam String cep,
                                @RequestParam String address,
                                @RequestParam(required = false) String birthDate) {
        patientService.updateProfile(auth.getName(), cpf, phone, cep, address, birthDate);
        return "redirect:/patient/profile?updated";
    }

    @GetMapping("/diseases")
    public String diseases(Authentication auth, Model model) {
        model.addAttribute("patient", patientService.getByUserEmail(auth.getName()));
        model.addAttribute("allDiseases", diseaseRepository.findAll());
        return "patient/diseases";
    }

    @PostMapping("/diseases/add")
    public String addDisease(Authentication auth, @RequestParam Long diseaseId) {
        patientService.addDisease(auth.getName(), diseaseId);
        return "redirect:/patient/diseases";
    }

    @PostMapping("/diseases/remove")
    public String removeDisease(Authentication auth, @RequestParam Long diseaseId) {
        patientService.removeDisease(auth.getName(), diseaseId);
        return "redirect:/patient/diseases";
    }
}