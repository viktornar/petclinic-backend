package com.viktornar.github.petclinic.repositories;

import com.viktornar.github.petclinic.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> { ;
    public User findByUsername(String username);
    public List<User> findAllByUsername(String username);
    public List<User> findAllByPassword(String password);
    public User findByUsernameAndPassword(String username, String password);
}
