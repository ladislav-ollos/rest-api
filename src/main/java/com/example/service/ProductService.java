package com.example.service;

import com.example.domain.ProductEntity;
import com.example.exception.NotFoundException;
import com.example.mapper.ProductMapper;
import com.example.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.example.schema.Product;

import jakarta.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * @author Ladislav
 *
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
     * @param id - {@link Product#getId()}
     * 
     * @return {@link Product}
     */
    public Product getProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(NotFoundException::new);
        return productMapper.toDto(productEntity);
    }
    
    /**
     * Get the list of all products.
     * 
     * @return list of {@link Product}
     */
    public Collection<Product> getProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productMapper.toDto(productEntities);
    }
    
    /**
     * Save a product.
     * 
     * @return {@link Product}
     */
    public Product saveProduct(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);
        ProductEntity saved = productRepository.save(productEntity);
        return productMapper.toDto(saved);
    }
}
