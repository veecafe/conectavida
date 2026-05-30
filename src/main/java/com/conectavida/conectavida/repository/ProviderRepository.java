package com.conectavida.conectavida.repository;

import com.conectavida.conectavida.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Optional<Provider> findByUserId(Long userId);

    List<Provider> findByProfession(String profession);

    List<Provider> findBySpecialtiesId(Long specialtyId);
}