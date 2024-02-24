package com.example.mapper;

import com.example.domain.SubscriptionEntity;
import com.example.schema.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses= {ProductMapper.class, UserMapper.class})
public interface SubscriptionMapper {

    Subscription toDto(SubscriptionEntity subscription);
    SubscriptionEntity toEntity(Subscription subscription);
}
