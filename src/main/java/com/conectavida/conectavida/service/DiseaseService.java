package com.conectavida.conectavida.service;

import com.conectavida.conectavida.model.Disease;
import com.conectavida.conectavida.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseService {

    @Autowired
    private DiseaseRepository diseaseRepository;

    public List<Disease> findAll() {
        return diseaseRepository.findAll();
    }

    public Disease create(String name, String description) {
        Disease disease = new Disease();
        disease.setName(name);
        disease.setDescription(description);
        return diseaseRepository.save(disease);
    }

    public void delete(Long id) {
        diseaseRepository.deleteById(id);
    }
}