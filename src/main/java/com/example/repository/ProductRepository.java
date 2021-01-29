package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.schema.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

}
