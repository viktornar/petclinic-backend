package com.viktornar.github.petclinic.repositories;

import com.viktornar.github.petclinic.models.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnersRepository extends CrudRepository<Owner, Long> {
    List<Owner> findAll();

    @Query("SELECT owner FROM Owner owner left join fetch owner.pets WHERE owner.id =:id")
    @Transactional(readOnly = true)
    Optional<Owner> findByIdWithPets(@Param("id") Long id);
}