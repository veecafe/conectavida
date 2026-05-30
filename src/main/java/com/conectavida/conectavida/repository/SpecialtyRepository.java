package com.conectavida.conectavida.repository;

import com.conectavida.conectavida.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
}