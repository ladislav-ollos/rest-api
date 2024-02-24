package com.example.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


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

    private LocalDateTime endDate;

    private boolean isPaused;

    public SubscriptionEntity(UserEntity user, ProductEntity product) {
        this.user = user;
        this.product = product;
    }

    @PrePersist
    void calculateStartAndEnd() {
        this.startDate = LocalDateTime.now();
        this.endDate = startDate.plus(product.getDuration(), ChronoUnit.MILLIS);
    }
}
