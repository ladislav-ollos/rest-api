package com.example.service;

import com.example.bo.Product;
import com.example.entity.ProductEntity;
import com.example.repository.ProductRepository;
import com.example.service.testutils.TestFactoryUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collection;

/**
 * @author Ladislav
 */
@DataJpaTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldReturnEmptyListOfProducts() {
        Collection<Product> products = productService.getProducts();
        Assertions.assertEquals(0, products.size(), "Product list should be empty.");
    }

    @Test
    void shouldReturnNonEmptyListOfProducts() {
        ProductEntity productEntity = TestFactoryUtil.createProductEntity();
        ProductEntity saved = productRepository.save(productEntity);

        Collection<Product> products = productService.getProducts();

        Assertions.assertEquals(1, products.size(), "Product list should contain 1 product.");
        Product product = products.iterator().next();
        Assertions.assertEquals(saved.getDescription(), product.getDescription());
        Assertions.assertEquals(saved.getId(), product.getId());
        Assertions.assertEquals(saved.getTax(), product.getTax());
        Assertions.assertEquals(saved.getName(), product.getName());
        Assertions.assertEquals(saved.getDuration(), product.getDuration());
        Assertions.assertEquals(saved.getPrice(), product.getPrice());
    }

    @Test
    void shouldSaveProduct() {
        Product product = TestFactoryUtil.createProduct();

        Product saved = productService.saveProduct(product);

        ProductEntity productEntity = productRepository.findById(saved.getId()).orElseThrow();
        Assertions.assertEquals(productEntity.getId(), saved.getId());
        Assertions.assertEquals(product.getDescription(), saved.getDescription());
        Assertions.assertEquals(product.getTax(), saved.getTax());
        Assertions.assertEquals(product.getName(), saved.getName());
        Assertions.assertEquals(product.getDuration(), saved.getDuration());
        Assertions.assertEquals(product.getPrice(), saved.getPrice());
    }

    @Test
    void shouldReturnSavedProduct() {
        ProductEntity productEntity = TestFactoryUtil.createProductEntity();
        ProductEntity saved = productRepository.save(productEntity);

        Product product = productService.getProduct(saved.getId());

        Assertions.assertEquals(saved.getDescription(), product.getDescription());
        Assertions.assertEquals(saved.getId(), product.getId());
        Assertions.assertEquals(saved.getTax(), product.getTax());
        Assertions.assertEquals(saved.getName(), product.getName());
        Assertions.assertEquals(saved.getDuration(), product.getDuration());
        Assertions.assertEquals(saved.getPrice(), product.getPrice());
    }

}
