package com.example.rest;

import com.example.schema.Product;
import com.example.schema.Subscription;
import com.example.schema.User;
import com.example.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * @author Ladislav
 */
@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    /**
     * Create a new subscription
     *
     * @param userId    -  {@link User#getId()}
     * @param productId -  {@link Product#getId()}
     * @return the newly created {@link Subscription}
     */
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Post new subscription for given user and product")
    @PostMapping(value = "/subscription", params = {"userId", "productId"})
    public Subscription subscription(@RequestParam(value = "userId")
                                     @Parameter(description = "id of subscribing user", required = true) Long userId,
                                     @RequestParam(value = "productId")
                                     @Parameter(description = "id of subscribed product", required = true) Long productId) {
        return subscriptionService.subscribe(userId, productId);
    }

    /**
     * Get subscription by ID.
     *
     * @param id -  the subscription ID
     * @return {@link Subscription}
     */
    @GetMapping(value = "/subscription/{id}")
    @Operation(summary = "Get existing subscription")
    public Subscription subscription(@PathVariable(value = "id") long id) {
        return subscriptionService.getSubscription(id);
    }

    /**
     * Delete subscription by ID.
     *
     * @param id -  the subscription ID
     */
    @DeleteMapping(value = "/subscription/{id}")
    @Operation(summary = "Cancel existing subscription")
    public void cancel(@PathVariable(value = "id") long id) {
        subscriptionService.delete(id);
    }

    /**
     * Pause subscription by ID.
     *
     * @param id -  the subscription ID
     */
    @PostMapping(value = "/subscription/{id}/pause")
    @Operation(summary = "Pause existing subscription", description = "Sets the paused flag. Depending on the semantics of pause/unpause the end date could be updated too.")
    public void pause(@PathVariable(value = "id") long id) {
        subscriptionService.pause(id);
    }

    /**
     * Unpause subscription by ID.
     *
     * @param id -  the subscription ID
     */
    @PostMapping(value = "/subscription/{id}/unpause")
    @Operation(summary = "Unpause existing subscription", description = "Unsets the paused flag. Depending on the semantics of pause/unpause the end date could be updated too.")
    public void unpause(@PathVariable(value = "id") long id) {
        subscriptionService.unpause(id);
    }
}
