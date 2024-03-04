package com.example.service;

import com.example.bo.User;
import com.example.entity.UserEntity;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collection;
import java.util.Set;

@DataJpaTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldReturnEmptyListOfUsers() {
        Collection<User> users = userService.getUsers();
        Assertions.assertEquals(0, users.size(), "User list should be empty.");
    }

    @Test
    void shouldReturnNonEmptyListOfUsers() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("username");
        userEntity.setSubscriptions(Set.of()); // TODO: add subscriptions
        UserEntity saved = userRepository.save(userEntity);

        Collection<User> users = userService.getUsers();

        Assertions.assertEquals(1, users.size(), "User list should contain 1 user.");
        User user = users.iterator().next();
        Assertions.assertEquals(saved.getId(), user.getId());
        Assertions.assertEquals(saved.getUserName(), user.getUserName());
        Assertions.assertEquals(saved.getSubscriptions().size(), user.getSubscriptions().size());
    }

    @Test
    void shouldReturnSavedUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("username");
        userEntity.setSubscriptions(Set.of()); // TODO: add subscriptions
        UserEntity saved = userRepository.save(userEntity);

        User user = userService.getUser(saved.getId());

        Assertions.assertEquals(saved.getId(), user.getId());
        Assertions.assertEquals(saved.getUserName(), user.getUserName());
        Assertions.assertEquals(saved.getSubscriptions().size(), user.getSubscriptions().size());
    }

}
