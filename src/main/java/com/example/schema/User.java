package com.example.schema;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class User {

    @Hidden
    private long id;

    @Schema(description = "username", example = "l.ollos")
    private String userName;

    @Hidden
    private Set<Subscription> subscriptions;

}
