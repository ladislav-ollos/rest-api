package com.example.service;

import com.example.exception.NotFoundException;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.schema.Product;

import java.util.List;
import java.util.Optional;

/**
 * @author Ladislav
 *
 */
@Component
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    /**
     * Get product by ID if present
     * @param id - {@link Subscription#id}
     * @throws {@link NotFoundException}
     * 
     * @return {@link Product}
     */
    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new NotFoundException();
    }
    
    /**
     * Get the list of all products.
     * 
     * @return list of {@link Product}
     */
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    
    /**
     * Save a product.
     * 
     * @return {@link Product}
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
