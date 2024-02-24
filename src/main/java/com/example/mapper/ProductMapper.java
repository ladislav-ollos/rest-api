package com.example.mapper;

import com.example.domain.ProductEntity;
import com.example.schema.Product;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Collection<Product> toDto(Collection<ProductEntity> product);
    Product toDto(ProductEntity product);
    Collection<ProductEntity> toEntity(Collection<Product> product);
    ProductEntity toEntity(Product product);
}
