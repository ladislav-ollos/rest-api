package com.example.mapper;

import com.example.bo.Product;
import com.example.entity.ProductEntity;
import com.example.schema.ProductDto;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Collection<ProductDto> toDto(Collection<Product> product);

    Collection<ProductEntity> toEntity(Collection<Product> product);

    ProductDto toDto(Product product);

    ProductEntity toEntity(Product product);


    Collection<Product> fromDto(Collection<ProductDto> productDtos);

    Product fromDto(ProductDto product);

    Collection<Product> fromEntity(Collection<ProductEntity> productEntities);

    Product fromEntity(ProductEntity productDto);
}
