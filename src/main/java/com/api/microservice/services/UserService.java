package com.api.microservice.services;

import com.api.microservice.models.UserModel;
import com.api.microservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Object save(UserModel user) {
        var encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public List<UserModel> fidAll() {
        return userRepository.findAll();
    }
}
