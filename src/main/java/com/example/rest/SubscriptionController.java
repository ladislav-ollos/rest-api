package com.example.rest;

import com.example.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;


@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Operation(summary = "Post new subscription for given user and product")
    @PostMapping(value = "/subscription", params = {"userId", "productId"})
    public void subscription(@RequestParam(value = "userId")
                                 @Parameter(description="id of subscribing user", required = true) Long userId,
                             @RequestParam(value = "productId")
                             @Parameter(description="id of subscribed product", required = true) Long productId) {
        subscriptionService.subscribe(userId, productId);
    }

    @GetMapping(value = "/subscription/{id}")
    @Operation(summary = "Get existing subscription")
    public void subscription(@PathParam(value = "id") long id) {
        subscriptionService.getSubscription(id);
    }

    @DeleteMapping(value = "/subscription/{id}")
    @Operation(summary = "Cancel existing subscription")
    public void cancel(@PathVariable (value = "id") long id) {
        subscriptionService.delete(id);
    }

    @PutMapping(value = "/subscription/{id}/pause")
    @Operation(summary = "Pause existing subscription", description = "Sets the paused flag. Depending on the semantics of pause/unpause the end date could be updated too.")
    public void pause(@PathVariable (value = "id") long id) {
        subscriptionService.pause(id);
    }

    @PutMapping(value = "/subscription/{id}/unpause")
    @Operation(summary = "Unpause existing subscription", description = "Unsets the paused flag. Depending on the semantics of pause/unpause the end date could be updated too.")
    public void unpause(@PathVariable(value = "id") long id) {
        subscriptionService.unpause(id);
    }
}