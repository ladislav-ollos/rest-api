package com.example.entity;

import jakarta.persistence.*;
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
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String userName;

    @OneToMany(mappedBy = "user")
    private Set<SubscriptionEntity> subscriptions;

}
