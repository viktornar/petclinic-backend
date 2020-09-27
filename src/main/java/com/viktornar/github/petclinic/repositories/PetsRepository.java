package com.viktornar.github.petclinic.repositories;

import com.viktornar.github.petclinic.models.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetsRepository extends CrudRepository<Pet, Long>{
    List<Pet> findAll();

    Optional<Pet> findByIdAndOwnerId(Long petId, Long ownerId);
}