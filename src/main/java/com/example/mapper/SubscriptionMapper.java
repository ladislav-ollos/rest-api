package com.example.mapper;

import com.example.bo.Subscription;
import com.example.entity.SubscriptionEntity;
import com.example.schema.SubscriptionDto;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, UserMapper.class})
public interface SubscriptionMapper {

    Collection<SubscriptionDto> toDto(Collection<Subscription> subscriptions);

    Collection<SubscriptionEntity> toEntity(Collection<Subscription> subscriptions);

    SubscriptionDto toDto(Subscription subscription);

    SubscriptionEntity toEntity(Subscription subscription);
    
    Collection<Subscription> fromDto(Collection<SubscriptionDto> subscriptionDtos);

    Subscription fromDto(SubscriptionDto subscriptionDto);

    Collection<Subscription> fromEntity(Collection<SubscriptionEntity> subscriptionEntities);

    Subscription fromEntity(SubscriptionEntity subscriptionEntity);
}
