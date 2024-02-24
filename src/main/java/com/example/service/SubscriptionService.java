package com.example.service;

import com.example.domain.SubscriptionEntity;
import com.example.exception.ConflictException;
import com.example.exception.NotFoundException;
import com.example.mapper.SubscriptionMapper;
import com.example.repository.SubscriptionRepository;
import com.example.schema.Product;
import com.example.schema.Subscription;
import com.example.schema.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
        return subscriptionMapper.toDto(subscriptionEntity);
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
        SubscriptionEntity saved = subscriptionRepository.save(subscriptionEntity);
        return subscriptionMapper.toDto(saved);
    }

    /**
     * Pause subscription by ID
     *
     * @param id - {@link Subscription#getId()}
     */
    public void pause(Long id) {
        SubscriptionEntity subscriptionEntity = subscriptionRepository.findById(id).orElseThrow(NotFoundException::new);
        if (subscriptionEntity.isPaused()) {
            throw new ConflictException("Already paused.");
        }
        // TODO: do whatever pause should do
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
        // TODO: do whatever unpause should do
        subscriptionEntity.setPaused(false);
        subscriptionRepository.save(subscriptionEntity);
    }

    /**
     * Delete subscription by ID
     *
     * @param id - {@link Subscription#getId()}
     */
    public void delete(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
