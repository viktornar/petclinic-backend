package com.viktornar.github.petclinic.repositories;

import com.viktornar.github.petclinic.models.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitsRepository extends CrudRepository<Visit, Long> {
    List<Visit> findAll();
    List<Visit> findByPetId(Long petId);
}