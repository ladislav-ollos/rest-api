package com.example.schema;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class User {

    @Id
    @Hidden
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Schema(description = "username", example = "l.ollos")
    private String userName;

    @Hidden
    @OneToMany(mappedBy="user")
    private Set<Subscription> subscriptions;

}
