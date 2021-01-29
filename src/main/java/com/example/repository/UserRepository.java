package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.schema.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

}
