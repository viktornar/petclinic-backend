package com.viktornar.github.petclinic.controllers;

import com.viktornar.github.petclinic.models.User;
import com.viktornar.github.petclinic.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UsersRestController extends ApiRestController {
    private final UsersRepository usersRepository;

    @GetMapping("/users")
    List<User> getAllUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password
    ) {
        if (username != null && !username.isEmpty()) {
            return this.usersRepository.findAllByUsername(username);
        }

        if (password != null && !password.isEmpty()) {
            return this.usersRepository.findAllByPassword(password);
        }

        return (List<User>) this.usersRepository.findAll();
    }

    @PostMapping("/users")
    User createUser(@RequestBody User user) {
        return this.usersRepository.save(user);
    }

    @GetMapping("/users/{id}")
    Optional<User> getUserById(@PathVariable Long id) {
        return this.usersRepository.findById(id);
    }

    @PutMapping("/users/{id}")
    User updateUserById(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return this.usersRepository.save(user);
    }
}
