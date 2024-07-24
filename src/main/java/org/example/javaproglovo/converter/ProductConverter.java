package org.example.javaproglovo.converter;

import org.example.javaproglovo.dto.ProductDto;
import org.example.javaproglovo.entity.ProductEntity;

public class ProductConverter {

    public static ProductDto toDto(ProductEntity productEntity) {
        return ProductDto.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .country(productEntity.getCountry())
                .price(productEntity.getPrice())
                .build();
    }

    public static ProductEntity toEntity(ProductDto productDto) {
        return ProductEntity.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .country(productDto.getCountry())
                .price(productDto.getPrice())
                .build();
    }
}
