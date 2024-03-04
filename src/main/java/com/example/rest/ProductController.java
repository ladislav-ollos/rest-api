package com.example.rest;

import com.example.mapper.ProductMapper;
import com.example.schema.ProductDto;
import com.example.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Ladislav
 */
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    /**
     * Get a product by ID
     *
     * @param id - {@link ProductDto#getId()}
     * @return {@link ProductDto}
     */
    @Operation(summary = "Return a product and basic information about it")
    @GetMapping(value = "/product/{id}")
    public ProductDto product(@PathVariable(value = "id") long id) {
        return productMapper.toDto(productService.getProduct(id));
    }

    /**
     * List all products
     *
     * @return list of {@link ProductDto}
     */
    @GetMapping(value = "/product")
    @Operation(summary = "Return the full list of products")
    public Collection<ProductDto> product() {
        return productMapper.toDto(productService.getProducts());
    }

    /**
     * Create a new product
     *
     * @param productDto - {@link ProductDto}
     * @return {@link ProductDto}
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/product")
    @Operation(summary = "Create a new product")
    public ProductDto product(@RequestBody ProductDto productDto) {
        return productMapper.toDto(productService.saveProduct(productMapper.fromDto(productDto)));
    }
}
