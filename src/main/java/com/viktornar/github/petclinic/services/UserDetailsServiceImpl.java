package com.viktornar.github.petclinic.services;

import com.viktornar.github.petclinic.models.User;
import com.viktornar.github.petclinic.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =
                usersRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not exist"));

        Set<GrantedAuthority> grantedAuthoritySet =
                user.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority(role.getName().name())
                ).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthoritySet
        );
    }
}
