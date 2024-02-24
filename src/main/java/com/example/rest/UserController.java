package com.example.rest;

import com.example.schema.Subscription;
import com.example.schema.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Ladislav
 *
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    /**
     * List all users. 
     * 
     * @return {@link Collection<User>}
     */
    @GetMapping(value = "/user")
    public Collection<User> user() {
        return userService.getUsers();
    }

    /**
     * Create a new user
     * @param user -  the {@link User} to save
     * 
     * @return the newly created {@link User}
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/user")
    public User user(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /**
     * Get user by ID.
     * @param id -  the user ID
     * 
     * @return {@link User}
     */
    @GetMapping(value = "/user/{id}")
    public User user(@PathVariable(value = "id") Long id) {
        return userService.getUser(id);
    }

    /**
     * List the subscriptions of the specified user.
     * @param id -  the user ID
     * 
     * @return {@link Collection<Subscription>}
     */
    @GetMapping(value = "/user/{id}/subscription")
    public Collection<Subscription> subscriptions(@PathVariable (value = "id") long id) {
        return userService.getUser(id).getSubscriptions();
    }
}
