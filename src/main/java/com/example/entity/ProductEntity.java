package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Duration;

/**
 * A product with name, price, description and a duration.
 *
 * @author Ladislav
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String description;

    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal tax;

    private Duration duration;

}
