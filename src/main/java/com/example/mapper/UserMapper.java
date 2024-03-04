package com.example.mapper;

import com.example.bo.User;
import com.example.entity.UserEntity;
import com.example.schema.UserDto;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = {SubscriptionMapper.class})
public interface UserMapper {

    Collection<UserDto> toDto(Collection<User> users);

    Collection<UserEntity> toEntity(Collection<User> users);

    UserDto toDto(User user);

    UserEntity toEntity(User user);


    Collection<User> fromDto(Collection<UserDto> userDtos);

    User fromDto(UserDto userDto);

    Collection<User> fromEntity(Collection<UserEntity> userEntities);

    User fromEntity(UserEntity userEntity);

}
