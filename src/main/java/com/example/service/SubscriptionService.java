package com.example.service;

import com.example.schema.Subscription;
import com.example.repository.ProductRepository;
import com.example.repository.SubscriptionRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public Subscription getSubscription(Long id) {
        return subscriptionRepository.findById(id).get();
    }

    public void subscribe(Long user, Long product) {
        Subscription subscription = new Subscription(userRepository.findById(user).get(), productRepository.findById(product).get());
        subscriptionRepository.save(subscription);
    }

    public void pause(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).get();
        // TODO: do whatever pause should do
        subscription.setPaused(true);
        subscriptionRepository.save(subscription);
    }

    public void unpause(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).get();
        // TODO: do whatever unpause should do
        subscription.setPaused(false);
        subscriptionRepository.save(subscription);
    }

    public void delete(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
