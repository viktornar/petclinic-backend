package com.viktornar.github.petclinic.controllers;

import com.viktornar.github.petclinic.exceptions.PetNotFoundException;
import com.viktornar.github.petclinic.exceptions.VisitNotFoundException;
import com.viktornar.github.petclinic.models.Visit;
import com.viktornar.github.petclinic.repositories.PetsRepository;
import com.viktornar.github.petclinic.repositories.VisitsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class VisitsRestController  extends ApiRestController{
    private final VisitsRepository visitsRepository;
    private final PetsRepository petsRepository;

    @GetMapping("/owners/*/pets/{petId}/visits")
    List<Visit> getVisits(@PathVariable("petId") Long petId) {
        return visitsRepository.findByPetId(petId);
    }

    @GetMapping("/owners/*/pets/{petId}/visits/{visitId}")
    List<Visit> getVisit(@PathVariable Long petId, @PathVariable Long visitId)
            throws PetNotFoundException, VisitNotFoundException {

        if (!petsRepository.existsById(petId)) {
            throw new PetNotFoundException(String.format("Pet with id %s not found", petId));
        }

        Visit visit = visitsRepository.findByIdAndPetId(visitId, petId)
                .orElseThrow(() -> new VisitNotFoundException(String.format("Visit with id %s not found", visitId)));

        return Collections.singletonList(visit);
    }

    @PostMapping("/owners/*/pets/{petId}/visits")
    List<Visit> postVisits(@PathVariable("petId") Long petId, @RequestBody Visit visit) throws PetNotFoundException {
        petsRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(String.format("Pet with id %s not found", petId)));
        visit.setPetId(petId);
        return Collections.singletonList(visitsRepository.save(visit));
    }

    @PutMapping("/owners/*/pets/{petId}/visits/{visitId}")
    List<Visit> putPet(@PathVariable Long petId, @PathVariable Long visitId, @RequestBody Visit visit)
            throws PetNotFoundException, VisitNotFoundException, IllegalAccessException, InvocationTargetException {

        if (!petsRepository.existsById(petId)) {
            throw new PetNotFoundException(String.format("Pet with id %s not found", petId));
        }

        Visit visitToUpdate = visitsRepository.findByIdAndPetId(visitId, petId)
                .orElseThrow(() -> new VisitNotFoundException(String.format("Visit with id %s not found", visitId)));

        BeanUtils.copyProperties(visitToUpdate, visit);

        visitToUpdate.setId(visitId);
        visitToUpdate.setPetId(petId);

        return Collections.singletonList(visitsRepository.save(visitToUpdate));
    }

    @DeleteMapping("/owners/*/pets/{petId}/visits/{visitId}")
    List<Visit> deletePet(@PathVariable Long petId, @PathVariable Long visitId)
            throws PetNotFoundException, VisitNotFoundException {
        if (!petsRepository.existsById(petId)) {
            throw new PetNotFoundException(String.format("Pet with id %s not found", petId));
        }

        if (!visitsRepository.existsById(visitId)) {
            throw new VisitNotFoundException(String.format("Visit with id %s not found", visitId));
        }

        visitsRepository.deleteById(visitId);

        return new ArrayList<>();
    }
}