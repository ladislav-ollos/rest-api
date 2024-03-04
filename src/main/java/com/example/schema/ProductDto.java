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
public class ProductDto {

    @Hidden
    private long id;

    @Schema(description = "product name", example = "Our top product", defaultValue = "Our top product")
    private String name;

    @Schema(description = "product description", example = "a cool product", defaultValue = "a cool product")
    private String description;

    @NotNull
    @Schema(description = "product price in USD", example = "1700", defaultValue = "1700")
    private BigDecimal price;

    @NotNull
    @Schema(description = "product tax in USD", example = "452.15", defaultValue = "452.15")
    private BigDecimal tax;

    @Schema(description = "product duration in milliseconds", example = "0", defaultValue = "0")
    private Duration duration;

}
