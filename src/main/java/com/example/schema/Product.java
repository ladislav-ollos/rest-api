package com.example.schema;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

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

    @Hidden
    private long id;

    @Schema(description = "product name", example = "Our top product", defaultValue = "Our top product")
    private String name;

    @Schema(description = "product description", example = "a cool product")
    private String description;

    @NotNull
    @Schema(description = "product price in USD", example = "1700", defaultValue = "1700", required = true)
    private BigDecimal price;

    @Schema(description = "product duration in milliseconds", example = "604800000", defaultValue = "604800000")
    private long duration;

}
