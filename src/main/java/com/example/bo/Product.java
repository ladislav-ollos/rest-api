package com.example.bo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.Duration;

/**
 * A product with name, price, description and a duration.
 *
 * @author Ladislav
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class Product {
    private long id;
    private String name;
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal tax;
    private Duration duration;
}
