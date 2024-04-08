package com.orion.mdd.services;

import com.orion.mdd.models.User;
import com.orion.mdd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public User getUser(final Integer id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User getUser(final String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    public void createUser(final String email, final String username, final String password) {
        // TODO Vérifier les critères de la validations mots de passe
        User user = User.builder()
                .email(email)
                .username(username)
                .password(passwordEncoder.encode(password))
                .posts(new HashSet<>())
                .comments(new HashSet<>())
                .build();

        this.userRepository.save(user);
    }

    public boolean existsByEmail(final String email) {
        return this.userRepository.existsByEmail(email);
    }

    public void update(User user) {
        this.userRepository.save(user);
    }
}
