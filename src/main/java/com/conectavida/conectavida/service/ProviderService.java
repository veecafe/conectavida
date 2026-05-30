package com.conectavida.conectavida.service;

import com.conectavida.conectavida.model.*;
import com.conectavida.conectavida.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private SpecialtyRepository specialtyRepository;
    @Autowired
    private UserRepository userRepository;

    public Provider getByUserEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return providerRepository.findByUserId(user.getId()).orElseThrow();
    }

    public Provider updateProfile(String email, String registrationNumber, String profession,
                                  String bio, String phone, String cep, String address) {
        Provider provider = getByUserEmail(email);
        provider.setRegistrationNumber(registrationNumber);
        provider.setProfession(profession);
        provider.setBio(bio);
        provider.setPhone(phone);
        provider.setCep(cep);
        provider.setAddress(address);
        return providerRepository.save(provider);
    }

    public void addSpecialty(String email, Long specialtyId) {
        Provider provider = getByUserEmail(email);
        Specialty specialty = specialtyRepository.findById(specialtyId).orElseThrow();
        provider.getSpecialties().add(specialty);
        providerRepository.save(provider);
    }

    public void removeSpecialty(String email, Long specialtyId) {
        Provider provider = getByUserEmail(email);
        provider.getSpecialties().removeIf(s -> s.getId().equals(specialtyId));
        providerRepository.save(provider);
    }

    public List<Provider> searchProviders(Long specialtyId, String profession) {
        if (specialtyId != null) {
            return providerRepository.findBySpecialtiesId(specialtyId);
        }
        if (profession != null && !profession.isBlank()) {
            return providerRepository.findByProfession(profession);
        }
        return providerRepository.findAll();
    }
}