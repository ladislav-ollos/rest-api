package com.example.rest;

import com.example.mapper.SubscriptionMapper;
import com.example.mapper.UserMapper;
import com.example.schema.SubscriptionDto;
import com.example.schema.UserDto;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Ladislav
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final SubscriptionMapper subscriptionMapper;

    /**
     * List all users.
     *
     * @return {@link Collection<UserDto>}
     */
    @GetMapping(value = "/user")
    public Collection<UserDto> user() {
        return userMapper.toDto(userService.getUsers());
    }

    /**
     * Create a new user
     *
     * @param userDto -  the {@link UserDto} to save
     * @return the newly created {@link UserDto}
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/user")
    public UserDto user(@RequestBody UserDto userDto) {
        return userMapper.toDto(userService.saveUser(userMapper.fromDto(userDto)));
    }

    /**
     * Get user by ID.
     *
     * @param id -  the user ID
     * @return {@link UserDto}
     */
    @GetMapping(value = "/user/{id}")
    public UserDto user(@PathVariable(value = "id") Long id) {
        return userMapper.toDto(userService.getUser(id));
    }

    /**
     * List the subscriptions of the specified user.
     *
     * @param id         -  the user ID
     * @param activeOnly - if true only the active subscriptions are returned
     * @return {@link Collection< SubscriptionDto >}
     */
    @GetMapping(value = "/user/{id}/subscription")
    public Collection<SubscriptionDto> subscriptions(@PathVariable(value = "id") long id, @RequestParam(value = "activeOnly", defaultValue = "false") boolean activeOnly) {
        return userService.getUser(id)
                .getSubscriptions()
                .stream()
                .filter(s -> !activeOnly || !s.isPaused() && !s.isCanceled())
                .map(subscriptionMapper::toDto)
                .toList();
    }
}
