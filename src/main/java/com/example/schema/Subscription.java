package com.example.schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class Subscription {

    @Id
    @Hidden
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnoreProperties({"subscriptions"})
    private User user;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
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

    @PrePersist
    void calculateStartAndEnd() {
        this.startDate = LocalDateTime.now();
        this.endDate = startDate.plus(product.getDuration(), ChronoUnit.MILLIS);
    }
}
