package com.conectavida.conectavida.service;

import com.conectavida.conectavida.model.Specialty;
import com.conectavida.conectavida.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }

    public Specialty create(String name) {
        Specialty specialty = new Specialty();
        specialty.setName(name);
        return specialtyRepository.save(specialty);
    }

    public void delete(Long id) {
        specialtyRepository.deleteById(id);
    }
}