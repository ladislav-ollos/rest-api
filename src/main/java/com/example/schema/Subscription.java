package com.example.schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * Represents a subscription of a user on a product.
 *
 * @author Ladislav
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class Subscription {

    @Hidden
    private long id;

    @JsonIgnoreProperties({"subscriptions"})
    private User user;

    private Product product;

    @Schema(description = "subscription start")
    private LocalDateTime startDate;

    @Schema(description = "subscription end")
    private LocalDateTime endDate;

    @Schema(description = "true indicates that the subscription is paused")
    boolean isPaused;

    public Subscription(User user, Product product) {
        this.user = user;
        this.product = product;
    }
}
