package com.example.rest;

import com.example.mapper.SubscriptionMapper;
import com.example.schema.ProductDto;
import com.example.schema.SubscriptionDto;
import com.example.schema.UserDto;
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
    private final SubscriptionMapper subscriptionMapper;

    /**
     * Create a new subscription
     *
     * @param userId    -  {@link UserDto#getId()}
     * @param productId -  {@link ProductDto#getId()}
     * @return the newly created {@link SubscriptionDto}
     */
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Post new subscription for given user and product")
    @PostMapping(value = "/subscription", params = {"userId", "productId"})
    public SubscriptionDto subscription(@RequestParam(value = "userId")
                                        @Parameter(description = "id of subscribing user", required = true) Long userId,
                                        @RequestParam(value = "productId")
                                        @Parameter(description = "id of subscribed product", required = true) Long productId) {
        return subscriptionMapper.toDto(subscriptionService.subscribe(userId, productId));
    }

    /**
     * Get subscription by ID.
     *
     * @param id -  the subscription ID
     * @return {@link SubscriptionDto}
     */
    @GetMapping(value = "/subscription/{id}")
    @Operation(summary = "Get existing subscription")
    public SubscriptionDto subscription(@PathVariable(value = "id") long id) {
        return subscriptionMapper.toDto(subscriptionService.getSubscription(id));
    }

    /**
     * Cancel subscription by ID.
     *
     * @param id -  the subscription ID
     */
    @DeleteMapping(value = "/subscription/{id}")
    @Operation(summary = "Cancel existing subscription")
    public void cancel(@PathVariable(value = "id") long id) {
        subscriptionService.cancel(id);
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
