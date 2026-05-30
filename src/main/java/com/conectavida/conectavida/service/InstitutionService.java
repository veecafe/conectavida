package com.conectavida.conectavida.service;

import com.conectavida.conectavida.model.*;
import com.conectavida.conectavida.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private UserRepository userRepository;

    public Institution getByUserEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return institutionRepository.findByUserId(user.getId()).orElseThrow();
    }

    public Institution updateProfile(String email, String cnpj, String description,
                                     String cep, String address, String phone, String website) {
        Institution institution = getByUserEmail(email);
        institution.setCnpj(cnpj);
        institution.setDescription(description);
        institution.setCep(cep);
        institution.setAddress(address);
        institution.setPhone(phone);
        institution.setWebsite(website);
        return institutionRepository.save(institution);
    }

    public void addProvider(String email, Long providerId) {
        Institution institution = getByUserEmail(email);
        Provider provider = providerRepository.findById(providerId).orElseThrow();
        institution.getProviders().add(provider);
        institutionRepository.save(institution);
    }

    public void removeProvider(String email, Long providerId) {
        Institution institution = getByUserEmail(email);
        institution.getProviders().removeIf(p -> p.getId().equals(providerId));
        institutionRepository.save(institution);
    }
}