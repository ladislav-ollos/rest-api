package com.example.service;

import com.example.schema.Subscription;
import com.example.repository.ProductRepository;
import com.example.repository.SubscriptionRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ladislav
 *
 */
@Component
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public SubscriptionService(@Autowired SubscriptionRepository subscriptionRepository, @Autowired UserRepository userRepository, @Autowired ProductRepository productRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    /**
     * Get subscription by ID
     * @param id - {@link Subscription#id}
     * 
     * @return {@link Subscription}
     */
    public Subscription getSubscription(Long id) {
        return subscriptionRepository.findById(id).get();
    }

    /**
     * Subscribes {@link User} to {@link Product} if both exist for the given ids.
     * @param user - {@link User#id}
     * @param product - {@link Product#id}
     * 
     * @return {@link Subscription}
     */
    public Subscription subscribe(Long user, Long product) {
        Subscription subscription = new Subscription(userRepository.findById(user).get(), productRepository.findById(product).get());
        subscriptionRepository.save(subscription);
        return subscription;
    }

    /**
     * Pause subscription by ID
     * @param id - {@link Subscription#id}
     * 
     * @return {@link Subscription}
     */
    public void pause(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).get();
        // TODO: do whatever pause should do
        subscription.setPaused(true);
        subscriptionRepository.save(subscription);
    }

    /**
     * Unpause subscription by ID
     * @param id - {@link Subscription#id}
     * 
     * @return {@link Subscription}
     */
    public void unpause(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).get();
        // TODO: do whatever unpause should do
        subscription.setPaused(false);
        subscriptionRepository.save(subscription);
    }

    /**
     * Delete subscription by ID
     * @param id - {@link Subscription#id}
     */
    public void delete(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
