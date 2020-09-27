package com.viktornar.github.petclinic.controllers;

import com.viktornar.github.petclinic.exceptions.OwnerNotFoundException;
import com.viktornar.github.petclinic.exceptions.PetNotFoundException;
import com.viktornar.github.petclinic.models.Owner;
import com.viktornar.github.petclinic.models.Pet;
import com.viktornar.github.petclinic.repositories.OwnersRepository;
import com.viktornar.github.petclinic.repositories.PetsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PetsRestController extends ApiRestController {
    private final OwnersRepository ownersRepository;
    private final PetsRepository petsRepository;

    @GetMapping("/owners/{ownerId}/pets")
    List<Pet> getPets(@PathVariable Long ownerId) throws OwnerNotFoundException {
        Owner owner = ownersRepository.findByIdWithPets(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Owner with id %s not found", ownerId)));

        return new ArrayList<>(owner.getPets());
    }

    @GetMapping("/owners/{ownerId}/pets/{petId}")
    List<Pet> getPet(@PathVariable Long ownerId, @PathVariable Long petId)
            throws OwnerNotFoundException, PetNotFoundException {
        Pet pet = petsRepository.findByIdAndOwnerId(petId, ownerId)
                .orElseThrow(() -> new PetNotFoundException(String.format("Pet with id %s not found", petId)));

        return Collections.singletonList(pet);
    }

    @PostMapping("/owners/{ownerId}/pets")
    List<Pet> postPet(@PathVariable Long ownerId, @RequestBody Pet pet) throws OwnerNotFoundException {
        Owner owner = ownersRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Owner with id %s not found", ownerId)));

        pet.setOwner(owner);

        return Collections.singletonList(petsRepository.save(pet));
    }

    @PutMapping("/owners/{ownerId}/pets/{petId}")
    List<Pet> putPet(@PathVariable Long ownerId, @PathVariable Long petId, @RequestBody Pet pet)
            throws OwnerNotFoundException, PetNotFoundException, IllegalAccessException, InvocationTargetException {
        Owner owner = ownersRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Owner with id %s not found", ownerId)));

        Pet petToUpdate = petsRepository.findByIdAndOwnerId(petId, ownerId)
                .orElseThrow(() -> new PetNotFoundException(String.format("Pet with id %s not found", petId)));

        BeanUtils.copyProperties(petToUpdate, pet);
        
        petToUpdate.setId(petId);
        petToUpdate.setOwner(owner);

        return Collections.singletonList(petsRepository.save(petToUpdate));
    }

    @DeleteMapping("/owners/{ownerId}/pets/{petId}")
    List<Pet> deletePet(@PathVariable Long ownerId, @PathVariable Long petId) throws OwnerNotFoundException,
            PetNotFoundException {
        petsRepository.findByIdAndOwnerId(petId, ownerId)
                .orElseThrow(() -> new PetNotFoundException(String.format("Pet with id %s not found", petId)));
        
        petsRepository.deleteById(petId);
        return new ArrayList<Pet>();
    }
}