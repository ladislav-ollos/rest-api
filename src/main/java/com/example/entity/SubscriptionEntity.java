package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;


/**
 * Represents a subscription of a user on a product.
 *
 * @author Ladislav
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
@Table(name = "SUBSCRIPTION")
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;


    private LocalDateTime startDate;
    private LocalDateTime projectedEndDate;
    private LocalDateTime lastUnpaused;
    private Duration durationLeftAtLastUnpause;

    private boolean isPaused;

    private boolean isCanceled;

}
