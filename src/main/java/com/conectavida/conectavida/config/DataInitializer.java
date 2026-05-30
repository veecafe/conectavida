package com.conectavida.conectavida.config;

import com.conectavida.conectavida.model.*;
import com.conectavida.conectavida.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private SpecialtyRepository specialtyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByEmail("admin@conectavida.com")) {
            User admin = new User();
            admin.setName("Administrador");
            admin.setEmail("admin@conectavida.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }

        if (diseaseRepository.count() == 0) {
            createDisease("Diabetes Tipo 1", "Doença autoimune que afeta a produção de insulina");
            createDisease("Diabetes Tipo 2", "Resistência à insulina");
            createDisease("Hipertensão", "Pressão arterial elevada");
            createDisease("Asma", "Doença respiratória crônica");
            createDisease("Artrite Reumatoide", "Doença autoimune que afeta as articulações");
            createDisease("Depressão", "Transtorno de humor persistente");
            createDisease("Ansiedade", "Transtorno de ansiedade generalizada");
        }

        if (specialtyRepository.count() == 0) {
            createSpecialty("Cardiologia");
            createSpecialty("Endocrinologia");
            createSpecialty("Psicologia Clínica");
            createSpecialty("Psiquiatria");
            createSpecialty("Fisioterapia Ortopédica");
            createSpecialty("Pneumologia");
            createSpecialty("Reumatologia");
            createSpecialty("Nutrição");
        }
    }

    private void createDisease(String name, String description) {
        Disease d = new Disease();
        d.setName(name);
        d.setDescription(description);
        diseaseRepository.save(d);
    }

    private void createSpecialty(String name) {
        Specialty s = new Specialty();
        s.setName(name);
        specialtyRepository.save(s);
    }
}