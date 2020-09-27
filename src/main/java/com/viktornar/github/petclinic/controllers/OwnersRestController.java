package com.viktornar.github.petclinic.controllers;

import com.viktornar.github.petclinic.exceptions.OwnerNotFoundException;
import com.viktornar.github.petclinic.models.Owner;
import com.viktornar.github.petclinic.repositories.OwnersRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OwnersRestController extends ApiRestController {
    private final OwnersRepository ownersRepository;

    @GetMapping("/owners")
    List<Owner> getOwners() {
        return ownersRepository.findAll();
    }

    @GetMapping("/owners/{id}")
    List<Owner> getOwner(@PathVariable Long id) throws OwnerNotFoundException {
        Owner owner = ownersRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Owner with id %s not found", id)));
        return Collections.singletonList(owner);
    }

    @PostMapping("/owners")
    List<Owner> postOwner(@RequestBody Owner owner) {
        return Collections.singletonList(ownersRepository.save(owner));
    }

    @PutMapping("/owners/{id}")
    List<Owner> putOwner(@RequestBody Owner owner, @PathVariable Long id)
            throws IllegalAccessException, InvocationTargetException, OwnerNotFoundException {
        Owner ownerForUpdate = ownersRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Owner with id %s not found", id)));
        owner.setId(id);
        BeanUtils.copyProperties(ownerForUpdate, owner);
        ownersRepository.save(ownerForUpdate);

        return Collections.singletonList(ownerForUpdate);
    }

    @DeleteMapping("/owners/{id}")
    List<Owner> deleteOwner(@PathVariable Long id) {
        ownersRepository.deleteById(id);
        return new ArrayList<Owner>();
    }
}
