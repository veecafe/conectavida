package com.conectavida.conectavida.controller;

import com.conectavida.conectavida.repository.SpecialtyRepository;
import com.conectavida.conectavida.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    private ProviderService providerService;
    @Autowired
    private SpecialtyRepository specialtyRepository;

    @GetMapping("/search")
    public String search(@RequestParam(required = false) Long specialtyId,
                         @RequestParam(required = false) String profession,
                         Model model) {
        model.addAttribute("providers", providerService.searchProviders(specialtyId, profession));
        model.addAttribute("specialties", specialtyRepository.findAll());
        return "search";
    }
}