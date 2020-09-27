package com.viktornar.github.petclinic.repositories;

import com.viktornar.github.petclinic.models.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitsRepository extends CrudRepository<Visit, Long> {
    List<Visit> findAll();
    List<Visit> findByPetId(Long petId);
    Optional<Visit> findByIdAndPetId(Long id, Long petId);
}