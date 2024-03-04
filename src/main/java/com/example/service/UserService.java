package com.example.service;

import com.example.bo.User;
import com.example.entity.UserEntity;
import com.example.exception.NotFoundException;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author Ladislav
 */
@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Get users by ID
     *
     * @param id - {@link User#getId()}
     * @return {@link User}
     */
    public User getUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(NotFoundException::new);
        return userMapper.fromEntity(userEntity);
    }

    /**
     * List all users
     *
     * @return {@link Collection<User>}
     */
    public Collection<User> getUsers() {
        return userMapper.fromEntity(userRepository.findAll());
    }

    /**
     * Saves the user. Generates a new {@link User#getId()}
     *
     * @param userDto - the {@link User} to save
     * @return the new {@link User}
     */
    public User saveUser(User userDto) {
        UserEntity userEntity = userMapper.toEntity(userDto);

        UserEntity saved = userRepository.save(userEntity);
        return userMapper.fromEntity(saved);
    }
}
