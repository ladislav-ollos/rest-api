package com.example.service;

import com.example.bo.Product;
import com.example.entity.ProductEntity;
import com.example.exception.NotFoundException;
import com.example.mapper.ProductMapper;
import com.example.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author Ladislav
 */
@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /**
     * Get product by ID if present
     *
     * @param id - {@link Product#getId()}
     * @return {@link Product}
     */
    public Product getProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(NotFoundException::new);
        return productMapper.fromEntity(productEntity);
    }

    /**
     * Get the list of all products.
     *
     * @return list of {@link Product}
     */
    public Collection<Product> getProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productMapper.fromEntity(productEntities);
    }

    /**
     * Save a product.
     *
     * @return {@link Product}
     */
    public Product saveProduct(Product productDto) {
        ProductEntity productEntity = productMapper.toEntity(productDto);
        ProductEntity saved = productRepository.save(productEntity);
        return productMapper.fromEntity(saved);
    }
}
