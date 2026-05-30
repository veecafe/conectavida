package com.conectavida.conectavida.service;

import com.conectavida.conectavida.model.*;
import com.conectavida.conectavida.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private UserRepository userRepository;

    public Patient getByUserEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return patientRepository.findByUserId(user.getId()).orElseThrow();
    }

    public Patient updateProfile(String email, String cpf, String phone, String cep, String address, String birthDate) {
        Patient patient = getByUserEmail(email);
        patient.setCpf(cpf);
        patient.setPhone(phone);
        patient.setCep(cep);
        patient.setAddress(address);
        if (birthDate != null && !birthDate.isBlank()) {
            patient.setBirthDate(java.time.LocalDate.parse(birthDate));
        }
        return patientRepository.save(patient);
    }

    public void addDisease(String email, Long diseaseId) {
        Patient patient = getByUserEmail(email);
        Disease disease = diseaseRepository.findById(diseaseId).orElseThrow();
        patient.getDiseases().add(disease);
        patientRepository.save(patient);
    }

    public void removeDisease(String email, Long diseaseId) {
        Patient patient = getByUserEmail(email);
        patient.getDiseases().removeIf(d -> d.getId().equals(diseaseId));
        patientRepository.save(patient);
    }
}