package com.example.repository;

import com.example.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Override
    List<UserEntity> findAll();

}
