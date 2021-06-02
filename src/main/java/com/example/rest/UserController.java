package com.example.rest;

import com.example.schema.Subscription;
import com.example.schema.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author Ladislav
 *
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }
    
    /**
     * List all users. 
     * 
     * @return list of {@link User}
     */
    @GetMapping(value = "/user")
    public List<User> user() {
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
     * @return List of {@link Subscription}
     */
    @GetMapping(value = "/user/{id}/subscription")
    public Set<Subscription> subscriptions(@PathVariable (value = "id") long id) {
        return userService.getUser(id).getSubscriptions();
    }
}
