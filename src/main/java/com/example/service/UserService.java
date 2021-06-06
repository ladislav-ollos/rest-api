package com.example.service;

import com.example.schema.User;
import com.example.exception.NotFoundException;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Ladislav
 *
 */
@Component
public class UserService {
    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get users by ID
     * @param id - {@link User#id}
     * 
     * @return {@link User}
     */
    public User getUser(Long id) {
        Optional<User> product = userRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new NotFoundException();
    }
    /**
     * List all users
     * 
     * @return list of {@link User}
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    /**
     * Saves the user. Generates a new {@link User#id}
     * @param user - the {@link User} to save
     * 
     * @return {@link User}
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
