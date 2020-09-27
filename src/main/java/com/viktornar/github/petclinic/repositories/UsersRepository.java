package com.viktornar.github.petclinic.repositories;

import com.viktornar.github.petclinic.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> { ;
    Optional<User> findByUsername(String username);
    List<User> findAllByUsername(String username);
    List<User> findAllByPassword(String password);
    User findByUsernameAndPassword(String username, String password);
}
