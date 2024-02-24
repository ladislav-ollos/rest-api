package com.example.mapper;

import com.example.domain.UserEntity;
import com.example.schema.User;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDto(UserEntity user);

    Collection<User> toDto(Collection<UserEntity> user);

    UserEntity toEntity(User user);

    Collection<UserEntity> toEntity(Collection<User> user);

}
