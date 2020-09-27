package com.viktornar.github.petclinic.repositories;

import com.viktornar.github.petclinic.models.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetsRepository extends CrudRepository<Pet, Long>{
    List<Pet> findAll();
}