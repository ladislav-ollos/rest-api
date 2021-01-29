package com.example.service;

import com.example.schema.User;
import com.example.exception.NotFoundException;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        Optional<User> product = userRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new NotFoundException();
    }
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
