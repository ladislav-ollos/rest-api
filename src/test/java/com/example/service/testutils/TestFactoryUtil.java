package com.example.service.testutils;

import com.example.bo.Product;
import com.example.entity.ProductEntity;

import java.math.BigDecimal;
import java.time.Duration;

public final class TestFactoryUtil {

    public static ProductEntity createProductEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("New product");
        productEntity.setDescription("A nice product description");
        productEntity.setDuration(Duration.ofDays(30));
        productEntity.setPrice(BigDecimal.ONE);
        productEntity.setTax(BigDecimal.ZERO);
        return productEntity;
    }

    public static Product createProduct() {
        Product product = new Product();
        product.setName("New product");
        product.setDescription("A nice product description");
        product.setDuration(Duration.ofDays(30));
        product.setPrice(BigDecimal.ONE);
        product.setTax(BigDecimal.ZERO);
        return product;
    }
}
