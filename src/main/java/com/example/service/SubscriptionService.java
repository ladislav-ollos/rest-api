package com.example.service;

import com.example.bo.Product;
import com.example.bo.Subscription;
import com.example.bo.User;
import com.example.entity.SubscriptionEntity;
import com.example.exception.ConflictException;
import com.example.exception.NotFoundException;
import com.example.mapper.SubscriptionMapper;
import com.example.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Ladislav
 */
@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserService userService;
    private final ProductService productService;

    /**
     * Get subscription by ID
     *
     * @param id - {@link Subscription#getId()}
     * @return {@link Subscription}
     */
    public Subscription getSubscription(Long id) {
        SubscriptionEntity subscriptionEntity = subscriptionRepository.findById(id)
                .or(() -> {
                    throw new NotFoundException();
                }).get();
        return subscriptionMapper.fromEntity(subscriptionEntity);
    }

    /**
     * Subscribes {@link User} to {@link Product} if both exist for the given ids.
     *
     * @param userId    - {@link User#getId()}
     * @param productId - {@link Product#getId()}
     * @return {@link Subscription}
     */
    public Subscription subscribe(Long userId, Long productId) {
        Subscription subscription = new Subscription(userService.getUser(userId), productService.getProduct(productId));
        SubscriptionEntity subscriptionEntity = subscriptionMapper.toEntity(subscription);
        LocalDateTime now = LocalDateTime.now();
        subscriptionEntity.setStartDate(now);
        subscriptionEntity.setPaused(false);
        SubscriptionEntity saved = subscriptionRepository.save(subscriptionEntity);
        return subscriptionMapper.fromEntity(saved);
    }

    /**
     * Pause subscription by ID
     *
     * @param id - {@link Subscription#getId()}
     */
    public void pause(Long id) {
        SubscriptionEntity subscriptionEntity = subscriptionRepository.findById(id).orElseThrow(NotFoundException::new);
        if (subscriptionEntity.isPaused() || subscriptionEntity.isCanceled()) {
            throw new ConflictException("Already paused or canceled.");
        }
        subscriptionEntity.setPaused(true);
        subscriptionRepository.save(subscriptionEntity);
    }

    /**
     * Unpause subscription by ID
     *
     * @param id - {@link Subscription#getId()}
     */
    public void unpause(Long id) {
        SubscriptionEntity subscriptionEntity = subscriptionRepository.findById(id).orElseThrow(NotFoundException::new);
        if (!subscriptionEntity.isPaused()) {
            throw new ConflictException("Not paused.");
        }
        if (subscriptionEntity.isCanceled()) {
            throw new ConflictException("Canceled subscription can not be unpaused.");
        }
        // TODO: do whatever unpause should do
        subscriptionEntity.setPaused(false);
        subscriptionEntity.setLastUnpaused(LocalDateTime.now());
        subscriptionRepository.save(subscriptionEntity);
    }


    /**
     * Cancel subscription by ID
     *
     * @param id - {@link Subscription#getId()}
     */
    public void cancel(Long id) {
        SubscriptionEntity subscriptionEntity = subscriptionRepository.findById(id).orElseThrow(NotFoundException::new);
        subscriptionEntity.setCanceled(true);
        subscriptionRepository.save(subscriptionEntity);
    }
}
