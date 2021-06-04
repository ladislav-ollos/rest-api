package com.example.rest;

import com.example.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.schema.Product;

import java.util.List;

/**
 * @author Ladislav
 *
 */
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(@Autowired ProductService productService) {
        this.productService = productService;
    }
    
    /**
     * Get a product by ID
     * @param id - {@link Product#id}
     * 
     * @return {@link Product}
     */
    @Operation(summary = "Return a product and basic information about it")
    @GetMapping(value = "/product/{id}")
    public Product product(@PathVariable(value = "id") long id) {
        return productService.getProduct(id);
    }

    /**
     * List all products
     * 
     * @return list of {@link Product}
     */
    @GetMapping(value = "/product")
    @Operation(summary = "Return the full list of products")
    public List<Product> product() {
        return productService.getProducts();
    }

    /**
     * Create a new product
     * @param product - {@link Product}
     * 
     * @return {@link Product}
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/product")
    @Operation(summary = "Create a new product")
    public Product product(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
}
