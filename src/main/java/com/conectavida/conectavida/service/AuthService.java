package com.conectavida.conectavida.service;

import com.conectavida.conectavida.model.*;
import com.conectavida.conectavida.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User registerPatient(String name, String email, String password) {
        validateEmail(email);
        User user = createUser(name, email, password, Role.PATIENT);

        Patient patient = new Patient();
        patient.setUser(user);
        patientRepository.save(patient);

        return user;
    }

    @Transactional
    public User registerProvider(String name, String email, String password) {
        validateEmail(email);
        User user = createUser(name, email, password, Role.PROVIDER);

        Provider provider = new Provider();
        provider.setUser(user);
        providerRepository.save(provider);

        return user;
    }

    @Transactional
    public User registerInstitution(String name, String email, String password) {
        validateEmail(email);
        User user = createUser(name, email, password, Role.INSTITUTION);

        Institution institution = new Institution();
        institution.setUser(user);
        institutionRepository.save(institution);

        return user;
    }

    private void validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email já cadastrado");
        }
    }

    private User createUser(String name, String email, String password, Role role) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        return userRepository.save(user);
    }
}