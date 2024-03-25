package com.orion.mdd.services;

import com.orion.mdd.models.User;
import com.orion.mdd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(final Integer id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User getUser(final String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }
}
