package com.example.bo;

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
@Getter
@Setter
@ToString
@Slf4j
@NoArgsConstructor
public class Subscription {
    private long id;
    private User user;
    private Product product;
    private LocalDateTime startDate;
    private LocalDateTime projectedEndDate;
    private LocalDateTime lastUnpaused;
    boolean isPaused;
    private Duration durationLeftAtLastUnpause;
    private boolean isCanceled;

    public Subscription(final User user, final Product product) {
        this.user = user;
        this.product = product;
        LocalDateTime now = LocalDateTime.now();
        this.startDate = now;
        this.lastUnpaused = now;
        this.durationLeftAtLastUnpause = product.getDuration();
        this.projectedEndDate = LocalDateTime.from(product.getDuration().addTo(now));
    }
}
