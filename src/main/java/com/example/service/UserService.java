package com.example.service;

import com.example.domain.UserEntity;
import com.example.exception.NotFoundException;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import com.example.schema.User;
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
        return userMapper.toDto(userEntity);
    }

    /**
     * List all users
     *
     * @return {@link Collection<User>}
     */
    public Collection<User> getUsers() {
        return userMapper.toDto(userRepository.findAll());
    }

    /**
     * Saves the user. Generates a new {@link User#getId()}
     *
     * @param user - the {@link User} to save
     * @return the new {@link User}
     */
    public User saveUser(User user) {
        UserEntity userEntity = userMapper.toEntity(user);

        UserEntity saved = userRepository.save(userEntity);
        return userMapper.toDto(saved);
    }
}
