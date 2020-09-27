package com.viktornar.github.petclinic.services;

import com.viktornar.github.petclinic.models.Role;
import com.viktornar.github.petclinic.models.User;
import com.viktornar.github.petclinic.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void save(User user) {
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPasswordConfirm());
        user.setPassword(encryptedPassword);
        user.setPasswordConfirm(encryptedPassword);

        if (user.getRoles() == null) {
            Role role = new Role();
            role.setName(Role.RoleName.USER);
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
        }

        usersRepository.save(user);
    }
}
