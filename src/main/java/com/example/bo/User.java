package com.example.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * Represents a user who can have multiple subscriptions.
 *
 * @author Ladislav
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class User {
    private long id;
    private String userName;
    private Set<Subscription> subscriptions;
}
