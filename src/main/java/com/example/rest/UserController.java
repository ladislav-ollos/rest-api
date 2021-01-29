package com.example.rest;

import com.example.schema.Subscription;
import com.example.schema.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public List<User> user() {
        return userService.getUsers();
    }

    @PostMapping(value = "/user")
    public User user(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping(value = "/user/{id}")
    public User user(@PathVariable(value = "id") Long id) {
        return userService.getUser(id);
    }

    @GetMapping(value = "/user/{id}/subscription")
    public Set<Subscription> subscriptions(@PathVariable (value = "id") long id) {
        return userService.getUser(id).getSubscriptions();
    }
}